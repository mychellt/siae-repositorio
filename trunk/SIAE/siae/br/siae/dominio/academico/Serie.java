package br.siae.dominio.academico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="serie", schema="academico")
public class Serie implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_SERIE")
	@SequenceGenerator(name="SEQ_SERIE", sequenceName="academico.seq_serie", allocationSize=1)
	@Column(name="id_serie")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
