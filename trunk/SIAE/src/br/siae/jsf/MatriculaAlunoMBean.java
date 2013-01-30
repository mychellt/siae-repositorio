package br.siae.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.dominio.academico.Turma;
import br.siae.service.MatriculaAlunoService;

@Controller
@Scope("session")
public class MatriculaAlunoMBean extends AbstractSiaeController<RequerimentoMatricula> implements ArqException{
	/** Listagem de alunos recuperados da busca. */
	private Collection<Aluno> alunos;
	
	/** Aluno selecionado para realização da matrícula. */
	private Aluno aluno;
	
	/** Turma selecionada para realização da matrícula.*/
	private Turma turma;
	
	
	@Resource(name="matriculaAlunoService")
	private MatriculaAlunoService service;
	
	public static final String FORM_MATRICULA = "/views/restrito/aluno/matricula.jsf";
	public static final String FORM_SELECAO_TURMA = "/views/restrito/aluno/selecao_turma.jsf";
	public static final String COMPROVANTE_MATRICULA = "/views/restrito/aluno/comprovante_matricula.jsf";
	
	public MatriculaAlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new RequerimentoMatricula();
	}
	public String iniciarMatricula() {
		 try {
			alunos = service.getAll(Aluno.class);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		aluno = new Aluno();
		return FORM_MATRICULA;
	}

	public String preSelecionar() {
		if( ValidatorUtil.isEmpty(aluno) ) {
			addMensagemErro("O elemento selecionado não existe mais no banco de dados.");
			return null;
		}
		if( ValidatorUtil.isNotEmpty( aluno.getRequerimentoMatricula() ) ){
			obj = aluno.getRequerimentoMatricula();
		}
		return FORM_SELECAO_TURMA;
	}
	
	public String matricular() {
		if( ValidatorUtil.isEmpty(turma) ) {
			addMensagemErro("Turma: campo obrigatório não informado.");
			return null;
		}
		
		try {
			service.executeRealizacaoMatricula(aluno, turma);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		
		return COMPROVANTE_MATRICULA;
	}
	
	public Collection<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Collection<Aluno> alunos) {
		this.alunos = alunos;
	}

	public MatriculaAlunoService getService() {
		return service;
	}

	public void setService(MatriculaAlunoService service) {
		this.service = service;
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return null;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
}
