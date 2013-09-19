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
@Table(name="sexo", schema="comum")
public class Sexo implements Persistent {
	public static final int MASCULINO = 1;
	public static final int FEMININO = 2;
	

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_SEXO" )
	@SequenceGenerator(allocationSize=1, sequenceName="seq_sexo", name="SEQ_SEXO")
	@Column(name="id_sexo")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@Column(name="sigla")
	private char sigla;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getSigla() {
		return sigla;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
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
		Sexo other = (Sexo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
