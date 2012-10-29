package br.siae.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dao.PessoaDAO;
import br.siae.arq.dominio.EspeciePessoa;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class AlunoMBean extends AbstractCrudController<Aluno>{

	@Autowired
	private PessoaMBean pessoaMBean;
	
	@Autowired
	private ConsultadorPessoaMBean consultadorPessoaMBean;
	
	@Resource(name="alunoService")
	private AlunoService alunoService;

	@Resource(name="pessoaDAO")
	private PessoaDAO dao;
	
	public AlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastro de Aluno");
		pessoaMBean.setControlador(this);
		return super.iniciarCadastro();
	}
	
	@Override
	public String iniciarListagem() {
		consultadorPessoaMBean.setControlador(this);
		try {
			consultadorPessoaMBean.setLista( (List<Pessoa>) dao.findByExactField( Pessoa.class, "especie", EspeciePessoa.ALUNO ));
		} catch (DAOException e) {
			addMensagemErro("Erro ao tentar recuperar os registro no base de dados, por favor contacte o administrador do sistema.");
		}
		return super.iniciarListagem();
	}
	
	
	public String preAlterar() {
		GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		obj =  dao.findByPrimaryKey( Aluno.class, getParameterInt("idAluno") );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		pessoaMBean.setObj( obj.getPessoa() );
		pessoaMBean.setControlador(this);
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
		obj.getPessoa().setEspecie( new EspeciePessoa( EspeciePessoa.ALUNO ) );
		
		try {
			obj = alunoService.executarCadastro( obj );
		} catch (NegocioException e) {
			e.printStackTrace();
			addMensagemErro( e.getMessage() );
		} catch (DAOException e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro ao tentar inserir o aluno na base de dados. Por favor entre em contato com o administrador do sistema.");
		}
		
		addMensagemInformacao("Cadastro do aluno efetuado com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	
	@Override
	public String getPaginaListagem() {
		return super.getPaginaListagem();
	}
}
