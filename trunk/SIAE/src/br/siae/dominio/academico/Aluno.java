package br.siae.dominio.academico;

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
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false, updatable=true)
	private Pessoa pessoa;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", insertable=true)
	private Usuario usuario;
	
	@Column(name="responsavel")
	private String responsavel;
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_requerimento_matricula", insertable=true)
	private RequerimentoMatricula requerimentoMatricula;
	
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

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public RequerimentoMatricula getRequerimentoMatricula() {
		return requerimentoMatricula;
	}

	public void setRequerimentoMatricula(RequerimentoMatricula requerimentoMatricula) {
		this.requerimentoMatricula = requerimentoMatricula;
	}
	
	public String getNomeExibicao() {
		return "Aluno";
	}
}