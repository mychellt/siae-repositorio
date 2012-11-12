package br.siae.dominio.comum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Persistable;
import br.siae.dominio.academico.Nivel;


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
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco", insertable=true, nullable=false, updatable=true)
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_modalidade", insertable=true, nullable=false, updatable=true)
	private Nivel modalidade;
	
	@Column(name="email")
	private String email;
	
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Nivel getModalidade() {
		return modalidade;
	}

	public void setModalidade(Nivel modalidade) {
		this.modalidade = modalidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}