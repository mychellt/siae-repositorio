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
@Table(name="nivel", schema="academico")
public class Nivel implements Persistable{
	public static final int EDUCACAO_INFANTIL = 1;
	public static final int ENSINO_FUNDAMENTAL_I = 2;
	public static final int ENSINO_FUNDAMENTAL_II = 3;
	public static final int ENSINO_MEDIO = 4;
	public static final int EJA = 5;
	public static final int ENSINO_TECNICO = 6;
	public static final int ENSINO_SUPERIOR = 7;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_NIVEL")
	@SequenceGenerator(name="SEQ_NIVEL", sequenceName="academico.seq_nivel", allocationSize=1)
	@Column(name="id_nivel")
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
		Nivel other = (Nivel) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
