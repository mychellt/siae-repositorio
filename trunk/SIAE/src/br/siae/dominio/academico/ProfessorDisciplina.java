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
import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.Turma;
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
	@JoinColumn(name="id_professor",nullable=false)
	private Professor professor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turma",nullable=false)
	private Turma turma;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina",nullable=false)
	private Disciplina disciplina;

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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}
