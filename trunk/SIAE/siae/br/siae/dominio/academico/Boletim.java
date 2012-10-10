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
@Table(name="boletim", schema="academico")
public class Boletim implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_BOLETIM")
	@SequenceGenerator(name="SEQ_BOLETIM", sequenceName="academico.seq_boletim", allocationSize=1)
	@Column(name="id_permissao")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
