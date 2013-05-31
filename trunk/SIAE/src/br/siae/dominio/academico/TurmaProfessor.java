package br.siae.dominio.academico;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Professor;


@Entity
@Table(name="turma_professor", schema="academico")
public class TurmaProfessor implements Persistable, Cloneable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_TURMA_PROFESSOR")
	@SequenceGenerator(name="SEQ_TURMA_PROFESSOR", sequenceName="academico.seq_turma_professor", allocationSize=1)
	@Column(name="id_turma_professor")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_professor",nullable=false)
	private Professor professor;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turma",nullable=false)
	private Turma turma;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="turmaProfessor")
	private Collection<DisciplinaTurmaProfessor> disciplinas;
	
	@Transient
	private Collection<DisciplinaTurmaProfessor> disciplinasRemoacao;

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

	public Collection<DisciplinaTurmaProfessor> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Collection<DisciplinaTurmaProfessor> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public String getDisciplinasExibicao() {
		StringBuffer exibicao  =  new StringBuffer();
		if( ValidatorUtil.isEmpty(disciplinas) ) return exibicao.toString();
		for( DisciplinaTurmaProfessor disciplina : disciplinas ) {
			exibicao.append( disciplina.getDisciplina().getNome() ).append(", ");			
		}
		exibicao = new StringBuffer( exibicao.substring(0, exibicao.length() - 2) );  				
		return exibicao.append(".").toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurmaProfessor other = (TurmaProfessor) obj;
		if (id != other.id)
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}

	public Collection<DisciplinaTurmaProfessor> getDisciplinasRemoacao() {
		if( ValidatorUtil.isEmpty(disciplinasRemoacao)) return new ArrayList<DisciplinaTurmaProfessor>();
		return disciplinasRemoacao;
	}

	public void setDisciplinasRemoacao(Collection<DisciplinaTurmaProfessor> disciplinasRemoacao) {
		this.disciplinasRemoacao = disciplinasRemoacao;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		TurmaProfessor tp =  (TurmaProfessor) super.clone();
		return tp;
	}

}
