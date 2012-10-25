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
@Table(name="endereco", schema="comum")
public class Endereco implements Persistable{
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ENDERECO")
	@SequenceGenerator(name="SEQ_ENDERECO", sequenceName="comum.seq_endereco", allocationSize=1)
	@Column(name="id_endereco")
	private long id;

	/** Bairro da pessoa/empresa */
	@Column(name="bairro")
	private String bairro;

	/** CEP da pessoa/empresa */
	@Column(name="cep")
	private String cep;

	/** Cidade da pessoa/empresa */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_municipio")
	private Municipio municipio;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_logradouro")
	private Logradouro logradouro;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@Column(name="numero")
	private long numero;

	public Endereco() {
		municipio = new Municipio();
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}

	

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
}
