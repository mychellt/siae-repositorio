package br.siae.jsf;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.ProfessorDisciplina;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.rh.Professor;
import br.siae.service.ProfessorService;

@Controller
@Scope("session")
public class ProfessorMBean extends AbstractSiaeController<Professor> implements ArqException{
	
	@Resource(name="pessoaMBean")
	private PessoaMBean pessoaMBean;
	
	@Resource(name="professorService")
	private ProfessorService professorService;
	
	public ProfessorMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Professor();
		obj.setDisciplinas( new ArrayList<ProfessorDisciplina>() );
		obj.setTurmas( new ArrayList<TurmaProfessor>() );
	}
	
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Professor");
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(true);
		return getPaginaCadastro();
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

		
		try {
			obj = professorService.executarCadastro( obj );
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		
		addMensagemErro("Cadastro do aluno efetuado com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}

	public String iniciarListagem() {
		return getPaginaListagem();
	}

	@Override
	public String processaException(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}
}
