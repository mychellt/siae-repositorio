package br.siae.arq.dominio;

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



/**
 * Classe de domínio que representa um município.
 * <br/>
 * @author Mychell Teixeira.
 * 
 *
 */
@Entity 
@Table(name="municipio", schema="comum") 
public class Municipio implements Persistable{
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
	public String getNome() {
		return nome;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Estado getEstado() {
		return estado;
	}
}
