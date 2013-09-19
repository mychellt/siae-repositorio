package br.arq.dominio;

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

import br.arq.dao.Persistent;
import br.arq.utils.StringUtils;


@Entity
@Table(name="pessoa", schema="comum")
public class Pessoa implements Persistent {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator="SEQ_PESSOA" )
	@SequenceGenerator(allocationSize=1, sequenceName="comum.seq_pessoa", name="SEQ_PESSOA")
	@Column(name="id_pessoa")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="nome_ascii")
	private String nomeAscii;
	
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
	@JoinColumn(name="id_sexo", nullable=true)
	private Sexo sexo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_tipo", nullable=false)
	private TipoPessoa tipo;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco", nullable=true, updatable=true, insertable=true)
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_identidade", nullable=true, insertable=true)
	private Identidade identidade;
	
	@Column(name="cpf")
	private Long cpf;
	
	@Column(name="passaporte")
	private String passaporte;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_certificado_militar", nullable= true, insertable=true, updatable=true)
	private CertificadoMilitar certificadoMilitar;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_certidao_nascimento", nullable=true, insertable=true, updatable=true)
	private CertidaoNascimento certidaoNascimento;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_titulo_eleitor", nullable=true, insertable=true, updatable=true)
	private TituloEleitor tituloEleitor;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_naturalidade", insertable=true, nullable=true, updatable=true)
	private Naturalidade naturalidade;
	
	@Column(name="nome_mae")
	private String nomeMae;
	
	@Column(name="nome_pai")
	private String nomePai;
	
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
		setNomeAscii( StringUtils.noAccents(nome).toUpperCase() );
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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public CertificadoMilitar getCertificadoMilitar() {
		return certificadoMilitar;
	}

	public void setCertificadoMilitar(CertificadoMilitar certificadoMilitar) {
		this.certificadoMilitar = certificadoMilitar;
	}

	public CertidaoNascimento getCertidaoNascimento() {
		return certidaoNascimento;
	}

	public void setCertidaoNascimento(CertidaoNascimento certidaoNascimento) {
		this.certidaoNascimento = certidaoNascimento;
	}

	public TituloEleitor getTituloEleitor() {
		return tituloEleitor;
	}

	public void setTituloEleitor(TituloEleitor tituloEleitor) {
		this.tituloEleitor = tituloEleitor;
	}

	public Naturalidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Naturalidade naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomeAscii() {
		return nomeAscii;
	}

	public void setNomeAscii(String nomeAscii) {
		this.nomeAscii = nomeAscii;
	}
}
