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
import br.siae.dominio.rh.Professor;


@Entity
@Table(name="turma_professor", schema="academico")
public class TurmaProfessor implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_TURMA_PROFESSOR")
	@SequenceGenerator(name="SEQ_TURMA_PROFESSOR", sequenceName="academico.seq_turma_professor", allocationSize=1)
	@Column(name="id_turma_professor")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_professor", insertable=true, nullable=false)
	private Professor professor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turma", insertable=true, nullable=false)
	private Turma turma;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
