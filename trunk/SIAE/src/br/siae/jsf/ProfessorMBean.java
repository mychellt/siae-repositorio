package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.primefaces.component.dialog.Dialog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
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
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public ProfessorMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Professor();
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
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		pessoaMBean.setObj( obj.getPessoa() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Professor");
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(false);
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validate();
		pessoaMBean.validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		obj.setPessoa( pessoaMBean.getObj() );
		obj.getPessoa().setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );

		
		try {
			obj = professorService.executeCadastro( obj );
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		
		addMensagemInformacao("Operação efetuada com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}

	
	public String carregarDados() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		Dialog componente = (Dialog) viewRoot.findComponent("form:info-cpf");
		if( ValidatorUtil.isEmpty( pessoaMBean.getCpf() ) ) {
			pessoaMBean.setMensagemErroCpf("Informe o número do CPF");
			pessoaMBean.setExibirInfoCpf(true);
			return null;
		}
		try {
			Pessoa pessoa = pessoaService.getByCpf( pessoaMBean.getCpf() );
			if( ValidatorUtil.isNotEmpty(pessoa) ) {
				pessoaMBean.setObj( pessoa );
				obj = professorService.getByPessoa( pessoa );
				setConfirmButton("Alterar");
				if( ValidatorUtil.isEmpty(obj) ) {
					resetObj();
					obj.setPessoa(pessoa);
					setConfirmButton("Cadastrar");
				}
			}
			else {
				pessoaMBean.resetObj();
				obj.getPessoa().setCpf(pessoaMBean.getCpf());				
			}
		} catch (Exception e) {
			if( !(e instanceof NoResultException ) ) {
				addMensagemErro( processaException(e) );
			}
		}
		pessoaMBean.setExibirInfoCpf(false);
		componente.setVisible(false);
		return null;
	}
	
	private void validate() {
		if( ValidatorUtil.isEmpty( obj.getLotacao()) ) {
			addMensagemErro("Lotação: campo obrigatório não informado.");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
	public Collection<Professor> getAll() {
		try {
			if( ValidatorUtil.isEmpty(ArqCache.getProfessores() ) ) {
				ArqCache.setProfessores( (List<Professor>) professorService.getAll(Professor.class) );				
			}
			return ArqCache.getProfessores();
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		return new ArrayList<Professor>();
	}
}
