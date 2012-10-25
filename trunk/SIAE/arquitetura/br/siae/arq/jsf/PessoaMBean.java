package br.siae.arq.jsf;

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
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class PessoaMBean extends AbstractCrudController<Pessoa>{
	public static final String COMPROVANTE_CADASTRO = "";
	private String descricaoCadastro = "Cadastrar Pessoa";
	private AbstractController controlador;
	
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
			addMensagemErro("Nome: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getDataNascimento() ) ) {
			addMensagemErro("Date de Nascimento: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getCpf() ) ) {
			addMensagemErro("CPF: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNomeMae() ) ) {
			addMensagemErro("Nome da Mãe: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getSexo() ) ) {
			addMensagemErro("Sexo: campo obrigatório não informado.");
		}
	}
}
