package br.siae.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.ProfessorDisciplina;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Professor;
import br.siae.service.TurmaService;

@Controller
@Scope("session")
public class TurmaMBean extends AbstractSiaeController<Turma> implements ArqException{
	private Professor professor;
	private Disciplina disciplina;
	private DisciplinaDataModel disciplinas;
	private Disciplina[] disciplinasSelecionadas;
	private Collection<Professor> professores;
	
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
		disciplinas = new DisciplinaDataModel();
		try {
			disciplinas.setWrappedData( service.getAll(Disciplina.class) );
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	public String inserirProfessor() {
		if( ValidatorUtil.isEmpty(professor) ) {
			addMensagemErro("Informe o docente da turma.");
		}
		if(ValidatorUtil.isEmpty( Arrays.asList(disciplinasSelecionadas) ) ) {
			addMensagemErro("Selecione pelo menos uma disciplina lecionada pelo professor.");
		}
		if(ValidatorUtil.isNotEmpty(professores) && professores.contains(professor) ) {
			addMensagemErro("O professor já foi inserido na turma");
		}
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			professor.setTurmas( service.getByExactField(TurmaProfessor.class, "professor.id", professor.getId() ) );
			professor.setDisciplinas( service.getByExactField(ProfessorDisciplina.class, "professor.id", professor.getId() ) );
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		if( ValidatorUtil.isEmpty( professor.getDisciplinas())) {
			professor.setDisciplinas( new ArrayList<ProfessorDisciplina>() );
		}
		
		if( ValidatorUtil.isEmpty(professor.getTurmas())) {
			professor.setTurmas( new ArrayList<TurmaProfessor>() );
		}
		
		
		boolean contemTurma = false;
		for( TurmaProfessor turmaProfessor : professor.getTurmas() ) {
			if( turmaProfessor.getProfessor().equals(professor) && turmaProfessor.getTurma().equals(obj) ) {
				contemTurma = true;
				break;
			}
		}
		
		for( Disciplina disiplina : Arrays.asList(disciplinasSelecionadas) ){
			ProfessorDisciplina pDisciplina = new ProfessorDisciplina();
			pDisciplina.setDisciplina(disiplina);
			pDisciplina.setProfessor(professor);
			pDisciplina.setTurma(obj);
			professor.getDisciplinas().add(pDisciplina);
		}
		
		
		if( !contemTurma ) {
			TurmaProfessor tp = new TurmaProfessor();
			tp.setProfessor(professor);
			tp.setTurma(obj);
			professor.getTurmas().add(tp);
		}
		
		
		if( ValidatorUtil.isEmpty(professores) ) {
			professores = new ArrayList<Professor>();
		}
		String stringExibicao = "";
		for( ProfessorDisciplina pd : professor.getDisciplinas() ){
			stringExibicao += pd.getDisciplina().getNome() + ",";
		}
		professor.setDisciplinasExibicao(stringExibicao);
		
		professores.add(professor);
		disciplinasSelecionadas = null;
		professor = new Professor();
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			service.executeCadastro(obj, professores );
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
			return getPaginaCadastro();
		}
		
		return getPaginaComprovante();
	}
	

	private void validate() {
		if( ValidatorUtil.isEmpty( obj.getAno() ) ) {
			addMensagemErro("Ano: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Nome: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getSerie() ) ) {
			addMensagemErro("Série: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getTurno() ) ) {
			addMensagemErro("Turno: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getNumeroVagas() ) ) {
			addMensagemErro("Número de Vagas: campo obrigatório não informado");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DisciplinaDataModel getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(DisciplinaDataModel disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina[] getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(Disciplina[] disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}

	public Collection<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Collection<Professor> professores) {
		this.professores = professores;
	}

}