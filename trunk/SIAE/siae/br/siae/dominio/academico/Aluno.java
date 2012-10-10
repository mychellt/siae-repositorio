package br.siae.dominio.academico;

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

import br.siae.arq.dominio.Persistable;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.Usuario;

@Entity
@Table(name="aluno", schema="academico")
public class Aluno implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ALUNO")
	@SequenceGenerator(name="SEQ_ALUNO", sequenceName="academico.seq_aluno", allocationSize=1)
	@Column(name="id_aluno")
	private long id;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false)
	private Pessoa pessoa;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", insertable=true)
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_mae", insertable=true, nullable=false)
	private Pessoa mae;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pai", insertable=true, nullable=true)
	private Pessoa pai;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_responsavel", insertable=true, nullable=true)
	private Pessoa responsavel;
	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_elemento_matricula", insertable=true, nullable=true)
	private ElementoMatricula elementoMatricula;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	public ElementoMatricula getElementoMatricula() {
		return elementoMatricula;
	}

	public void setElementoMatricula(ElementoMatricula elementoMatricula) {
		this.elementoMatricula = elementoMatricula;
	}
}
