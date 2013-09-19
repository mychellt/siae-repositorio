package br.arq.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.arq.dao.Persistent;


@Entity
@Table(name="certificado_militar", schema="comum")
public class CertificadoMilitar implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CERTIFICADO_MILITAR")
	@SequenceGenerator(name="SEQ_CERTIFICADO_MILITAR", sequenceName="comum.seq_certificado_militar", allocationSize=1)
	@Column(name="id_certificado_militar")
	private long id;
	
	@Column(name="numero")
	private String numero;
	
	@Column(name="serie")
	private String serie;
	
	@Column(name="categoria")
	private String categoria;
	
	@Column(name="orgao")
	private String orgao;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_expedicao")
	private Date dataExpedicao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
}
