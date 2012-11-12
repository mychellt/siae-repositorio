package br.siae.arq.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="especie_pessoa", schema="comum")
public class EspeciePessoa {
	public static final int ALUNO = 1;
	public static final int PROFESSOR = 2;
	public static final int FUNCIONARIO = 3;

	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_TIPO_PESSOA" )
	@SequenceGenerator(allocationSize=1, sequenceName="seq_tipo_pessoa", name="SEQ_TIPO_PESSOA")
	@Column(name="id_especie_pessoa")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;

	public EspeciePessoa() { }
	
	public EspeciePessoa(int id) { 
		this.id = id;
	}
	
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
