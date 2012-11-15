package br.siae.dominio.comum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="turno", schema="comum")
public class Turno implements Persistable{
	public static final int MANHA = 1;
	public static final int TARDE = 2;
	public static final int NOITE = 3;
	public static final int INTERMEDIARIO = 4;
	public static final int INTEGRAL = 5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_TURNO")
	@SequenceGenerator(name="SEQ_TURNO", sequenceName="comum.seq_turno", allocationSize=1)
	@Column(name="id_turno")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
}
