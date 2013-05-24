package br.siae.dominio.academico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="formacao_academica", schema="academico")
public class FormacaoAcademica implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_FORMACAO_ACADEMICA")
	@SequenceGenerator(name="SEQ_FORMACAO_ACADEMICA", sequenceName="academico.seq_formacao_academica", allocationSize=1)
	@Column(name="id_formacao_academica")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel_formacao", insertable=true, nullable=false)
	private NivelFormacao nivel;
	
	@Column(name="denominacao")
	private String denominacao;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public NivelFormacao getNivel() {
		return nivel;
	}

	public void setNivel(NivelFormacao nivel) {
		this.nivel = nivel;
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
		FormacaoAcademica other = (FormacaoAcademica) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
