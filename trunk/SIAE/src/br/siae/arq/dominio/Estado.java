package br.siae.arq.dominio;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * Classe que mapeia um estado da federação.
 * <br/>
 * @author Mychell Teixeira.
 */
@Entity
@Table(name="estado", schema="comum")
public class Estado implements Persistable{
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ESTADO")
	@SequenceGenerator(name="SEQ_ESTADO", sequenceName="seq_estado", allocationSize=1)
	@Column(name="id_estado")
	private long id;
	
	/** Nome do estado. */
	@Column(name="nome")
	private String nome;
	
	/** Sigla do estado. */
	@Column(name="sigla")
	private String sigla;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pais", nullable=false)
	private Pais pais;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="estado")
	private Collection<Municipio> municipios;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
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
		Estado other = (Estado) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
