package br.siae.matricula.web;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.erros.NegocioException;
import br.arq.jsf.GenericController;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.ItemRequerimentoMatricula;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.dominio.academico.Turma;
import br.siae.service.MatriculaAlunoService;

@Controller
@Scope("session")
public class ControllerMatriculaAluno extends GenericController<RequerimentoMatricula> implements ArqException{
	/** Formulário para matricula do aluno. */
	public static final String FORM_MATRICULA = "/views/restrito/matricula/form_matricula.jsf";
	
	/** Página para seleção do aluno a ser matriculado. */
	public static final String SELECAO_ALUNO_MATRICULA = "/views/restrito/matricula/selecao_aluno_matricula.jsf";
	
	/** Comprovante de matrícula.*/
	public static final String COMPROVANTE_MATRICULA = "/views/restrito/matricula/comprovante_matricula.jsf";
	
	/** Listagem de alunos recuperados da busca. */
	private Collection<Aluno> alunos;
	
	/** Aluno selecionado para realização da matrícula. */
	private Aluno aluno;
	
	/** Turma selecionada para realização da matrícula.*/
	private Turma turma;
	
	
	@Resource(name="matriculaAlunoService")
	private MatriculaAlunoService service;
	
	public ControllerMatriculaAluno() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new RequerimentoMatricula();
	}
	
	public String iniciarMatricula() {
		return SELECAO_ALUNO_MATRICULA;
	}
	
	public String preRealizarMatricula() {
		if( ValidatorUtil.isEmpty(aluno) ){
			addMensagemErro("O elemento selecionado não existe mais no banco de dados.");
			return null;
		}
		return FORM_MATRICULA;
	}
	
	public String preEmitirComprovanteMatricula() {
		if( ValidatorUtil.isEmpty(aluno) ){
			addMensagemErro("O elemento selecionado não existe mais no banco de dados.");
			return null;
		}
		try {
			aluno.setRequerimentoMatricula(service.getRequerimentoAluno(aluno));
			RequerimentoMatricula requerimentoMatricula = aluno.getRequerimentoMatricula();
			requerimentoMatricula.setItens(service.getByExactField(ItemRequerimentoMatricula.class, "requerimentoMatricula.id", requerimentoMatricula.getId()));
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
			return null;
		}
		return COMPROVANTE_MATRICULA;
	}

	public String preSelecionar() {
		if( ValidatorUtil.isEmpty(aluno) ) {
			addMensagemErro("O elemento selecionado não existe mais no banco de dados.");
			return null;
		}
		if( ValidatorUtil.isNotEmpty( aluno.getRequerimentoMatricula() ) ){
			obj = aluno.getRequerimentoMatricula();
		}
		return FORM_MATRICULA;
	}
	
	public String iniciarEmissaoComprovante() {
		return iniciarMatricula();
	}
	
	public String matricular() {
		ControllerConsultadorTurma consultadorTurma = (ControllerConsultadorTurma)getMBean("consultadorTurmaMBean");
		Turma[] arrayTurma = consultadorTurma.getArrayTurma();
		List<Turma> turmas = Arrays.asList(arrayTurma);	
		if( ValidatorUtil.isEmpty(turmas) ) {
			addMensagemErro("Turma: campo obrigatório não informado.");
			return null;
		}
		
		try {
			obj = service.executeRealizacaoMatricula(turmas,aluno);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
			return FORM_MATRICULA;
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
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe um(a) Aluno(a) cadastrado(o) com o cpf informado.";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
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
