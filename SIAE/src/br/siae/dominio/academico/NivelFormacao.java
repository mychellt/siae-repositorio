package br.siae.dominio.academico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arq.dao.Persistent;


@Entity
@Table(name="nivel_formacao", schema="academico")
public class NivelFormacao implements Persistent{
	
	public static final int GRADUACAO = 1;
	public static final int POS_GRADUACAO = 2;
	public static final int MESTRADO = 3;
	public static final int DOUTORADO = 4;
	public static final int POS_DOTOURADO = 5;
	public static final int TECNICO = 6;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_NIVEL_FORMACAO")
	@SequenceGenerator(name="SEQ_NIVEL_FORMACAO", sequenceName="academico.seq_nivel_formacao", allocationSize=1)
	@Column(name="id_nivel_formacao")
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
		NivelFormacao other = (NivelFormacao) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
