package br.siae.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="sexo", schema="comum")
public class Sexo implements Persistable {
	public static final int MASCULINO = 1;
	public static final int FEMININO = 2;
	

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_SEXO" )
	@SequenceGenerator(allocationSize=1, sequenceName="seq_sexo", name="SEQ_SEXO")
	@Column(name="id_sexo")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@Column(name="sigla")
	private char sigla;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getSigla() {
		return sigla;
	}

	public void setSigla(char sigla) {
		this.sigla = sigla;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

}
