package br.arq.dominio;

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
import javax.persistence.Transient;

import br.arq.dao.Persistent;
import br.arq.utils.ValidatorUtil;


@Entity
@Table(name="endereco", schema="comum")
public class Endereco implements Persistent{
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
	
	@Transient
	private Estado estado;

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
	
	public String getNomeExibicao() {
		if( ValidatorUtil.isEmpty(logradouro) && ValidatorUtil.isEmpty(getDenominacao()) && ValidatorUtil.isEmpty(getNumero()) )  return "N�o Informado"; 
		return ValidatorUtil.isNotEmpty( logradouro ) ? logradouro.getDenominacao() + ", " : "" + 
			   ( ValidatorUtil.isNotEmpty( getDenominacao() ) ? getDenominacao() : "" ) + 
			   ( ValidatorUtil.isNotEmpty( getNumero() ) ?  " - " + getNumero() : "");
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
