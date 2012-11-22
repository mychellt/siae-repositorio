package br.siae.jsf;

import javax.annotation.Resource;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.primefaces.component.dialog.Dialog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.ArqException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class AlunoMBean extends AbstractSiaeController<Aluno> implements ArqException{

	@Resource(name="pessoaMBean")
	private PessoaMBean pessoaMBean;
	
	@Resource(name="alunoService")
	private AlunoService service;
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public AlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
	}
	
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastro de Aluno");
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(true);
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	public String iniciarListagem() {	
		
		return getPaginaListagem();
	}
	
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		obj.getPessoa().getEndereco().setEstado( ValidatorUtil.isNotEmpty( obj.getPessoa().getEndereco().getMunicipio() ) ?  
				obj.getPessoa().getEndereco().getMunicipio().getEstado() : new Estado() );
		obj.getPessoa().getNaturalidade().setEstado( ValidatorUtil.isNotEmpty( obj.getPessoa().getNaturalidade().getMunicipio() ) ?  
				obj.getPessoa().getNaturalidade().getMunicipio().getEstado() : new Estado() );
		
		pessoaMBean.setObj( obj.getPessoa() );
		
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(false);
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		pessoaMBean.validate();
		if( isContemErros() ) {
			pessoaMBean.setExibirInfoCpf(false);
			return getPaginaCadastro();
		}
		
		obj.setPessoa( pessoaMBean.getObj() );
		obj.getPessoa().setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		
		try {
			obj = service.executeCadastro( obj );
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		
		addMensagemInformacao("Cadastro do aluno efetuado com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	public String remover() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = service.executeRemocao(obj);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaListagem();
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
				obj = service.getByPessoa( pessoa );
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

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe um(a) Aluno(a) cadastrado(o) com o cpf informado.";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}
}
