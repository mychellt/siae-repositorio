package br.siae.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="tipo_pessoa", schema="comum")
public class TipoPessoa implements Persistable{
	public final static int PESSOA_FISICA = 1;
	public final static int PESSOA_JURIDICA = 2;
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_TIPO_PESSOA" )
	@SequenceGenerator(allocationSize=1, sequenceName="seq_tipo_pessoa", name="SEQ_TIPO_PESSOA")
	@Column(name="id_tipo_pessoa")
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
	
	public boolean isPessoaFisica() {
		return id == PESSOA_FISICA;
	}
	
	public boolean isPessoaJuridica() {
		return id == PESSOA_JURIDICA;
	}
}
