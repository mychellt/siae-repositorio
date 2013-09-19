package br.arq.dominio;

import javax.persistence.CascadeType;
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



/**
 * Classe de domínio que representa um município.
 * <br/>
 * @author Mychell Teixeira.
 * 
 *
 */
@Entity 
@Table(name="municipio", schema="comum") 
public class Municipio implements Persistent{
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_MUNICIPIO")
	@SequenceGenerator(name="SEQ_MUNICIPIO", sequenceName="seq_municipio", allocationSize=1)
	@Column(name="id_municipio")
	private long id;
	
	/** Nome da cidade. */
	@Column(name="nome")
	private String nome;
	
	/** Nome do estado. */
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="id_estado",nullable=false)
	private Estado estado;
	
	public Municipio() {
		estado = new Estado();
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
		Municipio other = (Municipio) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getNome() {
		return nome;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Estado getEstado() {
		return estado;
	}
	
	public String getNomeExibicao() {
		return nome + " (" + estado.getSigla() + ")";
	}
	
}
