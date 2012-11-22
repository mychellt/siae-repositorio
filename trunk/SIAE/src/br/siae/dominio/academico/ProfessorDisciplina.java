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
import javax.persistence.UniqueConstraint;

import br.siae.arq.dominio.Persistable;
import br.siae.dominio.rh.Professor;

@Entity
@Table(name="professor_disciplina", schema="academico")
public class ProfessorDisciplina implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PROFESSOR_DISCIPLINA")
	@SequenceGenerator(name="SEQ_PROFESSOR_DISCIPLINA", sequenceName="academico.seq_professor_disciplina", allocationSize=1)
	@Column(name="id_professor_disciplina", nullable=false)
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina", nullable=false)
	private Disciplina disciplina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_professor", nullable=false)
	private Professor professor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
}
