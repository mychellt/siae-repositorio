package br.siae.arq.jsf;

import javax.annotation.Resource;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.primefaces.component.dialog.Dialog;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.CertidaoNascimento;
import br.siae.arq.dominio.CertificadoMilitar;
import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Identidade;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Naturalidade;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TituloEleitor;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class PessoaMBean extends AbstractSiaeController<Pessoa> implements ArqException{
	/** Xhtml para o comprovante de cadastro da pessoa.*/
	public static final String COMPROVANTE_CADASTRO = "/views/restrito/pessoa/comprovante.jsf";
	
	/** Descri��o da opera��o a ser realizada: cadastrar aluno, cadastrar professor ou cadastrar funcion�rio.*/
	private String descricaoCadastro = "Cadastrar Pessoa";
	
	/** Controlador manipulador.*/
	private AbstractController controlador;
	
	/** Indica que o Dialog para informar o cpf da pessoa deve ser exibido.*/
	private boolean exibirInfoCpf;
	
	/** Mensagem de valida��o para indica��o do cpf.*/
	private String mensagemErroCpf;
	
	/** Cpf informado no in�cio do cadastro. */
	private long cpf;
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public PessoaMBean() {
		resetObj();
	}

	public void resetObj() {
		obj = new Pessoa();
		obj.setEndereco( new Endereco() );
		obj.getEndereco().setLogradouro( new Logradouro() );
		obj.setIdentidade( new Identidade() );
		obj.setNaturalidade( new Naturalidade() );
		obj.setTituloEleitor( new TituloEleitor() );
		obj.setCertificadoMilitar( new CertificadoMilitar() );
		obj.setCertidaoNascimento( new CertidaoNascimento() );
	}

	public String getDescricaoCadastro() {
		return descricaoCadastro;
	}

	public void setDescricaoCadastro(String descricaoCadastro) {
		this.descricaoCadastro = descricaoCadastro;
	}
	
	public AbstractController getControlador() {
		return controlador;
	}

	public void setControlador(AbstractController controlador) {
		this.controlador = controlador;
	}
	
	public void validate( ) {
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Nome: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getDataNascimento() ) ) {
			addMensagemErro("Date de Nascimento: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getCpf() ) ) {
			addMensagemErro("CPF: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNomeMae() ) ) {
			addMensagemErro("Nome da M�e: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getSexo() ) ) {
			addMensagemErro("Sexo: campo obrigat�rio n�o informado.");
		}
	}
	public String carregarDados() {
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		Dialog componente = (Dialog) viewRoot.findComponent("form:info-cpf");
		if( ValidatorUtil.isEmpty( getCpf() ) ) {
			setMensagemErroCpf("Informe o n�mero do CPF");
			setExibirInfoCpf(true);
			return null;
		}
		try {
			obj = pessoaService.getByCpf( getCpf() );
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		if( ValidatorUtil.isEmpty(obj)) {
			resetObj();
			obj.setCpf(getCpf());
		}
		setExibirInfoCpf(false);
		componente.setVisible(false);
		return null;
	}

	public boolean isExibirInfoCpf() {
		return exibirInfoCpf;
	}

	public void setExibirInfoCpf(boolean exibirInfoCpf) {
		this.exibirInfoCpf = exibirInfoCpf;
	}

	public String getMensagemErroCpf() {
		return mensagemErroCpf;
	}

	public void setMensagemErroCpf(String mensagemErroCpf) {
		this.mensagemErroCpf = mensagemErroCpf;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "J� existe uma Pessoa cadastrada com esse cpf";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}
}
