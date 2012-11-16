package br.siae.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.ConsultadorPessoa;
import br.siae.dominio.rh.Funcionario;
import br.siae.service.FuncionarioService;

@Controller
@Scope("session")
public class ConsultadorFuncionarioMBean extends AbstractSiaeController<Funcionario> implements ArqException{
	
	/** Indica que a busca de será feita por matrícula do aluno.*/
	private boolean byMatricula;
	
	@Resource(name="consultadorPessoa")
	private ConsultadorPessoa consultadorPessoa; 
	
	@Resource(name="funcionarioService")
	private FuncionarioService funcionarioService;
	
	public ConsultadorFuncionarioMBean() {
		resetObj();
		lista = new ArrayList<Funcionario>();
	}
	
	private void resetObj() {
		obj = new Funcionario();
		obj.setPessoa( new Pessoa() );
		obj.getPessoa().setEndereco( new Endereco() );
		obj.getPessoa().getEndereco().setLogradouro( new Logradouro() );
		obj.getPessoa().setIdentidade( new Identidade() );
		obj.getPessoa().setNaturalidade( new Naturalidade() );
		obj.getPessoa().setTituloEleitor( new TituloEleitor() );
		obj.getPessoa().setCertificadoMilitar( new CertificadoMilitar() );
		obj.getPessoa().setCertidaoNascimento( new CertidaoNascimento() );	
	}
	
	public void validarConsultar() {
		consultadorPessoa.validarConsulta(obj.getPessoa());
	}
	public boolean isByMatricula() {
		return byMatricula;
	}
	public void setByMatricula(boolean byMatricula) {
		this.byMatricula = byMatricula;
	}
	
	public String iniciarListagem() {
		resetObj();
		try {
			lista =  (List<Funcionario>) funcionarioService.getAll(Funcionario.class);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaListagem();
	}
	
	public String consultar() {
		return null;
	}
	public ConsultadorPessoa getConsultadorPessoa() {
		return consultadorPessoa;
	}
	public void setConsultadorPessoa(ConsultadorPessoa consultadorPessoa) {
		this.consultadorPessoa = consultadorPessoa;
	}

	@Override
	public String processaException(Exception e) {
		return null;
	}
}
