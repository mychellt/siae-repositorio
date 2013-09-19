package br.siae.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.dominio.CertidaoNascimento;
import br.arq.dominio.CertificadoMilitar;
import br.arq.dominio.Endereco;
import br.arq.dominio.Identidade;
import br.arq.dominio.Logradouro;
import br.arq.dominio.Naturalidade;
import br.arq.dominio.Pessoa;
import br.arq.dominio.TituloEleitor;
import br.arq.erros.ArqException;
import br.siae.dominio.rh.Professor;
import br.siae.service.ProfessorService;

@Controller
@Scope("session")
public class ConsultadorProfessorMBean extends AbstractSiaeController<Professor> implements ArqException{
	
	/** Indica que a busca de será feita por matrícula do aluno.*/
	private boolean byMatricula;
	
	@Resource(name="consultadorPessoa")
	private ConsultadorPessoa consultadorPessoa; 
	
	@Resource(name="professorService")
	private ProfessorService professorService;
	
	public ConsultadorProfessorMBean() {
		resetObj();
		lista = new ArrayList<Professor>();
	}
	
	private void resetObj() {
		obj = new Professor();
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
			lista =  (List<Professor>) professorService.getAll(Professor.class);
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
