package br.siae.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.EspeciePessoa;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.ProfessorDisciplina;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.rh.Professor;
import br.siae.service.ProfessorService;

@Controller
@Scope("session")
public class ProfessorMBean extends AbstractCrudController<Professor>{
	
	@Autowired
	private PessoaMBean pessoaMBean;
	
	@Resource(name="professorService")
	private ProfessorService professorService;
	
	@Autowired
	private ConsultadorPessoaMBean consultadorPessoaMBean;
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public ProfessorMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Professor();
		obj.setDisciplinas( new ArrayList<ProfessorDisciplina>() );
		obj.setTurmas( new ArrayList<TurmaProfessor>() );
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Professor");
		pessoaMBean.setControlador(this);
		return super.iniciarCadastro();
	}
	

	public String preAlterar() {
		GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		obj =  dao.findByPrimaryKey( Professor.class, obj.getId() );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		pessoaMBean.validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		obj.setPessoa( pessoaMBean.getObj() );
		obj.getPessoa().setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		obj.getPessoa().setEspecie( new EspeciePessoa( EspeciePessoa.PROFESSOR ) );

		
		try {
			obj = professorService.executarCadastro( obj );
		} catch (NegocioException e) {
			e.printStackTrace();
			addMensagemErro( e.getMessage() );
		} catch (DAOException e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro ao tentar inserir o aluno na base de dados. Por favor entre em contato com o administrador do sistema.");
		}
		
		addMensagemErro("Cadastro do aluno efetuado com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	@Override
	public String iniciarListagem() {
		consultadorPessoaMBean.setControlador(this);
		try {
			consultadorPessoaMBean.setLista( (List<Pessoa>) dao.findByExactField( Pessoa.class, "especie", EspeciePessoa.PROFESSOR));
		} catch (DAOException e) {
			addMensagemErro("Erro ao tentar recuperar os registro no base de dados, por favor contacte o administrador do sistema.");
		}
		return super.iniciarListagem();
	}
}
