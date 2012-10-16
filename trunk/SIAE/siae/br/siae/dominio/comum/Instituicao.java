package br.siae.dominio.comum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;


@Entity
@Table(name="instituicao", schema="comum")
public class Instituicao implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_INSTITUICAO")
	@SequenceGenerator(name="SEQ_INSTITUICAO", sequenceName="comum.seq_instituicao", allocationSize=1)
	@Column(name="id_instituicao")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="codigo_inep")
	private String codigoInep;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoInep() {
		return codigoInep;
	}

	public void setCodigoInep(String codigoInep) {
		this.codigoInep = codigoInep;
	}

}
