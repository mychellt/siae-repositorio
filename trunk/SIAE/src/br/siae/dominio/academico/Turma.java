package br.siae.dominio.academico;

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

import br.siae.arq.dominio.Persistable;
import br.siae.dominio.comum.Turno;

@Entity
@Table(name="turma", schema="academico")
public class Turma implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_TURMA")
	@SequenceGenerator(name="SEQ_TURMA", sequenceName="academico.seq_turma", allocationSize=1)
	@Column(name="id_turma")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_serie", insertable=true)
	private Serie serie;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turno", insertable=true)
	private Turno turno;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="numero_vagas")
	private long numeroVagas;
	
	@Column(name="ano")
	private long ano;
	
	@OneToMany(mappedBy="turma", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Collection<TurmaProfessor> professores;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public long getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(long numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public long getAno() {
		return ano;
	}

	public void setAno(long ano) {
		this.ano = ano;
	}

	public Collection<TurmaProfessor> getProfessores() {
		return professores;
	}

	public void setProfessores(Collection<TurmaProfessor> professores) {
		this.professores = professores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Turma other = (Turma) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
