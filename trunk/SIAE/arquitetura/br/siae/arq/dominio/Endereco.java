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
	
	/** Endereço da pessoa/empresa */
	@Column(name="endereco")
	private String endereco;

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

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
}
