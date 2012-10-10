package br.siae.dominio.academico;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.dominio.Usuario;


@Entity
@Table(name="elemento_matricula", schema="academico")
public class ElementoMatricula implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ELEMENTO_MATRICULA")
	@SequenceGenerator(name="SEQ_ELEMENTO_MATRICULA", sequenceName="academico.seq_elemento_matricula", allocationSize=1)
	@Column(name="id_elemento_matricula")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_aluno", insertable=true)
	private Aluno aluno;
	
	@Column(name="numero_matricula")
	private long numeroMatricula;
	
	@Column(name="ano")
	private int ano;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_serie", insertable=true)
	private Serie serie;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_responsavel_cadastro", insertable=true)
	private Usuario responsavelCadastro;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public long getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(long numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Usuario getResponsavelCadastro() {
		return responsavelCadastro;
	}

	public void setResponsavelCadastro(Usuario responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}
}
