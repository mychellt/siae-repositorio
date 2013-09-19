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


@Entity
@Table(name="disciplina_nivel", schema="academico")
public class DisciplinaNivel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA_NIVEL")
	@SequenceGenerator(name="SEQ_DISCIPLINA_NIVEL", sequenceName="academico.seq_disciplina_nivel", allocationSize=1)
	@Column(name="id_disciplina_nivel")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina", insertable=true)
	private Disciplina disciplina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel", insertable=true)
	private Nivel nivel;

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

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
}
