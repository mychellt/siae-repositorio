package br.siae.arq.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="permissao", schema="seguranca")
public class Permissao  implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PERMISSAO")
	@SequenceGenerator(name="SEQ_PERMISSAO", sequenceName="seguranca.seq_permissao", allocationSize=1)
	@Column(name="id_permissao")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@Column(name="descricao")
	private String descricao;
	
	public String getDenominacao() {
		return denominacao;
	}
	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}