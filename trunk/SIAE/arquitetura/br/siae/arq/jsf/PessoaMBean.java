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

@Controller
@Scope("session")
public class PessoaMBean extends AbstractCrudController<Pessoa>{
	
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
}
