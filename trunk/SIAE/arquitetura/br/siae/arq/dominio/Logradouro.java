package br.siae.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="logradouro", schema="comum")
public class Logradouro implements Persistable{
	
	public static final int RUA = 1;
	public static final int AVENIDA = 2;
	public static final int BLOCO = 3;
	public static final int TRAVESSA = 4;
	public static final int PRA�A = 5;
	public static final int OUTROS  = 6;
	public static final int NAO_INFORMADO = 7;
	
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_LOGRADOURO")
	@SequenceGenerator(name="SEQ_LOGRADOURO", sequenceName="comum.seq_logradouro", allocationSize=1)
	@Column(name="id_logradouro")
	private long id;
	
	@Column(name="denominacao	")
	private String denominacao;

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
}
