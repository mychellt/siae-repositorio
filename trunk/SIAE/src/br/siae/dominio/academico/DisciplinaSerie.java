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
@Table(name="disciplina_serie", schema="academico")
public class DisciplinaSerie implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA_SERIE")
	@SequenceGenerator(name="SEQ_DISCIPLINA_SERIE", sequenceName="academico.seq_disciplina_serie", allocationSize=1)
	@Column(name="id_disciplina_serie")
	private long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_serie", insertable=true)
	private Serie serie;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina", insertable=true)
	private Disciplina disciplina;
	
	@Column(name="carga_horaria")
	private int cargaHoraria;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
}
