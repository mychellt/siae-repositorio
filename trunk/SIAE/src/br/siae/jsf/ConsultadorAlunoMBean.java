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
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class ConsultadorAlunoMBean extends AbstractSiaeController<Aluno> implements ArqException{
	
	/** Indica que a busca de ser� feita por matr�cula do aluno.*/
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
			lista =  (List<Aluno>) alunoService.getAll( Aluno.class );
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
