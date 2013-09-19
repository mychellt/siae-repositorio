package br.siae.dominio.rh;

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

import br.arq.dao.Persistent;


@Entity
@Table(name="cargo", schema="rh")
public class Cargo implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CARGO")
	@SequenceGenerator(name="SEQ_CARGO", sequenceName="rh.seq_cargo", allocationSize=1)
	@Column(name="id_cargo")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@Column(name="sigla")
	private String sigla;
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel_funcional", nullable=false)
	private NivelFuncional nivelFuncional;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public NivelFuncional getNivelFuncional() {
		return nivelFuncional;
	}

	public void setNivelFuncional(NivelFuncional nivelFuncional) {
		this.nivelFuncional = nivelFuncional;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
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
		Cargo other = (Cargo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
