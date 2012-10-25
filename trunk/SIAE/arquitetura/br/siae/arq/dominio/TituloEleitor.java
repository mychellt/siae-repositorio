package br.siae.arq.dominio;

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

@Entity
@Table(name="titulo_eleitor", schema="comum")
public class TituloEleitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_TITULO_ELEITOR")
	@SequenceGenerator(name="SEQ_TITULO_ELEITOR", sequenceName="comum.seq_titulo_eleitor", allocationSize=1)
	@Column(name="id_titulo_eleitor")
	private long id;
	
	@Column(name="numero")
	private long numero;
	
	@Column(name="zona")
	private String zona;
	
	@Column(name="secao")
	private String secao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_estado")
	private Estado uf;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

	public String getSecao() {
		return secao;
	}

	public void setSecao(String secao) {
		this.secao = secao;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}
}
