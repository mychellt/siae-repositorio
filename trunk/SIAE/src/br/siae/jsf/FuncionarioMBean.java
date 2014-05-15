package br.siae.jsf;

import javax.annotation.Resource;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.primefaces.component.dialog.Dialog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.dao.GenericDAO;
import br.arq.dominio.Pessoa;
import br.arq.dominio.TipoPessoa;
import br.arq.erros.ArqException;
import br.arq.jsf.GenericController;
import br.arq.jsf.PessoaMBean;
import br.arq.service.PessoaService;
import br.arq.service.ServiceFactory;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Funcionario;
import br.siae.service.FuncionarioService;

@Controller
@Scope("session")
public class FuncionarioMBean extends GenericController<Funcionario> implements ArqException{

	@Resource(name="pessoaMBean")
	private PessoaMBean pessoaMBean;
	@Resource(name="funcionarioService")
	private FuncionarioService funcionarioService;
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public  FuncionarioMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Funcionario();
	}
	
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Funcionário");
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(true);
		return getPaginaCadastro();
	}
	
	public String preAlterar() {
		GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		obj =  dao.findByPrimaryKey( Funcionario.class, obj.getId() );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		pessoaMBean.setExibirInfoCpf(false);
		pessoaMBean.setControlador(this);
		pessoaMBean.setObj(obj.getPessoa());
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
			obj = funcionarioService.executeCadastro( obj );
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		
		addMensagemInformacao("Operação realizada com sucesso!");
		return PessoaMBean.getComprovanteCadastro();
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
				obj = funcionarioService.getByPessoa( pessoa );
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
				pessoaMBean.getObj().setCpf(pessoaMBean.getCpf());
			}
		} catch (Exception e) {
			if( !(e instanceof NoResultException ) ) {
				addMensagemErro( processaException(e) );
			}
			else {
				pessoaMBean.resetObj();
				obj.getPessoa().setCpf(pessoaMBean.getCpf());
				pessoaMBean.getObj().setCpf(pessoaMBean.getCpf());
			}
		}
		pessoaMBean.setExibirInfoCpf(false);
		componente.setVisible(false);
		return null;
	}

	
	public String iniciarListagem() {
		return getPaginaListagem();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe um(a) Funcionário(a) cadastrado(o) com o cpf informado.";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}
}
