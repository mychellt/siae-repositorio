package br.siae.arq.dominio;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="identidade", schema="comum")
public class Identidade implements Persistable {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_IDENTIDADE" )
	@SequenceGenerator(allocationSize=1, sequenceName="comum.seq_identidade", name="SEQ_IDENTIDADE")
	@Column(name="id_identidade")
	private long id;
	
	@Column(name="numero")
	private long numero;
	
	@Column(name="orgao_expedidor")
	private String orgaoExpedidor;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_expedicao")
	private Date dataExpedicao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_estado")
	private Estado estado;

	public Identidade() {
		estado = new Estado();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
