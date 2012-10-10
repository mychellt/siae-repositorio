package br.siae.arq.dominio;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="pessoa", schema="comum")
public class Pessoa implements Persistable {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_PESSOA" )
	@SequenceGenerator(allocationSize=1, sequenceName="comum.seq_pessoa", name="SEQ_PESSOA")
	@Column(name="id_pessoa")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefone")
	private String telefone;
	
	@Column(name="celular")
	private String celular;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_sexo", nullable=false, updatable=true)
	private Sexo sexo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo", nullable=false, updatable=true)
	private TipoPessoa tipo;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco", nullable=false, updatable=true)
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_identidade", nullable=false)
	private Identidade identidade;
	
	/** Filiza��o*/
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_mae")
	private Pessoa mae;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pai")
	private Pessoa pai;	
	
	public Pessoa() {
		sexo = new Sexo();
		tipo = new TipoPessoa();
		endereco = new Endereco();
		identidade = new Identidade();
	}
	
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Identidade getIdentidade() {
		return identidade;
	}

	public void setIdentidade(Identidade identidade) {
		this.identidade = identidade;
	}

	public Pessoa getMae() {
		return mae;
	}

	public void setMae(Pessoa mae) {
		this.mae = mae;
	}

	public Pessoa getPai() {
		return pai;
	}

	public void setPai(Pessoa pai) {
		this.pai = pai;
	}

}
