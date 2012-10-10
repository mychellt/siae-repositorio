package br.siae.dominio.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="professor", schema="rh")
public class Professor implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PROFESSOR")
	@SequenceGenerator(name="SEQ_PROFESSOR", sequenceName="rh.seq_professor", allocationSize=1)
	@Column(name="id_professor")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
