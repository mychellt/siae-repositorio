package br.siae.dominio.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="professor", schema="rh")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_FUNCIONARIO")
	@SequenceGenerator(name="SEQ_FUNCIONARIO", sequenceName="rh.seq_funcionario", allocationSize=1)
	@Column(name="id_funcionario")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
