package br.siae.dominio.academico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="disciplina_turma_professor", schema="academico")
public class DisciplinaTurmaProfessor implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA_TURMA_PROFESSOR")
	@SequenceGenerator(name="SEQ_DISCIPLINA_TURMA_PROFESSOR", sequenceName="academico.seq_disciplina_turma_professor", allocationSize=1)
	@Column(name="id_disciplina_turma_professor")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turma_professor",nullable=false)
	private TurmaProfessor turmaProfessor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina",nullable=false)
	private Disciplina disciplina;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public TurmaProfessor getTurmaProfessor() {
		return turmaProfessor;
	}

	public void setTurmaProfessor(TurmaProfessor turmaProfessor) {
		this.turmaProfessor = turmaProfessor;
	}
}
