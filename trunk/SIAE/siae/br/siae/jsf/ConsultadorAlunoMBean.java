package br.siae.jsf;

import java.util.ArrayList;

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
import br.siae.arq.erro.DAOException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.ConsultadorPessoa;
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class ConsultadorAlunoMBean extends AbstractSiaeController<Aluno>{
	
	/** Indica que a busca de será feita por matrícula do aluno.*/
	private boolean byMatricula;
	
	@Resource(name="consultadorPessoa")
	private ConsultadorPessoa consultadorPessoa; 
	
	@Resource(name="alunoService")
	private AlunoService alunoService;
	
	public ConsultadorAlunoMBean() {
		resetObj();
		lista = new ArrayList<Aluno>();
	}
	
	private void resetObj() {
		obj = new Aluno();
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
			lista =  alunoService.getAll();
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registro de aluno. Por favor entre em contato com o administrado do sistema.");
			e.printStackTrace();
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
}
