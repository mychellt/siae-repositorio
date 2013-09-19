package br.siae.dominio.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arq.dao.Persistent;


@Entity
@Table(name="categoria", schema="rh")
public class Categoria implements Persistent {
	public static final int DOCENTE = 1;
	public static final int TECNICO_ADMINISTRATIVO = 2;
	public static final int NAO_ESPECIFICADO = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CATEGORIA")
	@SequenceGenerator(name="SEQ_CATEGORIA", sequenceName="rh.seq_categoria", allocationSize=1)
	@Column(name="id_categoria", nullable=false)
	private long id;
	
	@Column(name="denominacao", nullable=false)
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
		Categoria other = (Categoria) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
