package br.siae.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Professor;
import br.siae.service.TurmaService;

@Controller
@Scope("session")
public class TurmaMBean extends AbstractSiaeController<Turma> implements ArqException{
	private ProfessorDataModel professores;
	private Collection<Professor> professoresTurma;
	
	@Resource(name="turmaService")
	private TurmaService service;
	
	public TurmaMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
		obj.setNome( new String() );
	}
	
	public String iniciarCadastro() {
		resetObj();
		setConfirmButton("Cadastrar");
		try {
			professores = new ProfessorDataModel();
			professores.setWrappedData( service.getAll(Professor.class) );
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
	
	public Collection<Professor> getProfessoresTurma() {
		return professoresTurma;
	}

	public void setProfessoresTurma(Collection<Professor> professoresTurma) {
		this.professoresTurma = professoresTurma;
	}

	public ProfessorDataModel getProfessores() {
		return professores;
	}

	public void setProfessores(ProfessorDataModel professores) {
		this.professores = professores;
	}

}