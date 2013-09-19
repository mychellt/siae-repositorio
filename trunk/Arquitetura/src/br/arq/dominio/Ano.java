package br.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arq.dao.Persistent;

@Entity
@Table(name="ano", schema="comum")
public class Ano implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ANO")
	@SequenceGenerator(name="SEQ_ANO", sequenceName="comum.seq_ano", allocationSize=1)
	@Column(name="id_ano")
	private long id;
	
	@Column(name="denominacao")
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
