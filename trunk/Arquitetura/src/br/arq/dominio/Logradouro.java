package br.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arq.dao.Persistent;



@Entity
@Table(name="logradouro", schema="comum")
public class Logradouro implements Persistent{
	
	public static final int RUA = 1;
	public static final int AVENIDA = 2;
	public static final int BLOCO = 3;
	public static final int TRAVESSA = 4;
	public static final int PRACA = 5;
	public static final int OUTROS  = 6;
	public static final int NAO_INFORMADO = 7;
	
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_LOGRADOURO")
	@SequenceGenerator(name="SEQ_LOGRADOURO", sequenceName="comum.seq_logradouro", allocationSize=1)
	@Column(name="id_logradouro")
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
		Logradouro other = (Logradouro) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
