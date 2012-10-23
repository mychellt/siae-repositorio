package br.siae.dominio.rh;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.dominio.Pessoa;
import br.siae.dominio.academico.ProfessorDisciplina;
import br.siae.dominio.academico.TurmaProfessor;

@Entity
@Table(name="professor", schema="rh")
public class Professor implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PROFESSOR")
	@SequenceGenerator(name="SEQ_PROFESSOR", sequenceName="rh.seq_professor", allocationSize=1)
	@Column(name="id_professor")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false)
	private Pessoa pessoa;
	
	@OneToMany(mappedBy="turma", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<TurmaProfessor> turmas;
	
	@OneToMany(mappedBy="disciplina", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<ProfessorDisciplina> disciplinas;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<TurmaProfessor> getTurmas() {
		return turmas;
	}

	public void setTurmas(Collection<TurmaProfessor> turmas) {
		this.turmas = turmas;
	}

	public Collection<ProfessorDisciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Collection<ProfessorDisciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
}
