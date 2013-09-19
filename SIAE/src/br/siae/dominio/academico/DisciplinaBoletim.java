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

import br.arq.dao.Persistent;


@Entity
@Table(name="disciplina_boletim", schema="academico")
public class DisciplinaBoletim implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA_BOLETIM")
	@SequenceGenerator(name="SEQ_DISCIPLINA_BOLETIM", sequenceName="academico.seq_disciplina_boletim", allocationSize=1)
	@Column(name="id_disciplina_boletim")
	private long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_disciplina", insertable=true)
	private Disciplina disciplina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_boletim", insertable=true)
	private Boletim boletim;
	
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

	public Boletim getBoletim() {
		return boletim;
	}

	public void setBoletim(Boletim boletim) {
		this.boletim = boletim;
	}
}
