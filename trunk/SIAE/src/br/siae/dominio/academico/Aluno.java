package br.siae.dominio.academico;

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

import br.arq.dao.Persistent;
import br.arq.dominio.Pessoa;

@Entity
@Table(name="aluno", schema="academico")
public class Aluno implements Persistent{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ALUNO")
	@SequenceGenerator(name="SEQ_ALUNO", sequenceName="academico.seq_aluno", allocationSize=1)
	@Column(name="id_aluno")
	private long id;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false, updatable=true)
	private Pessoa pessoa;
	
	@Column(name="responsavel")
	private String responsavel;
	
	@OneToOne(mappedBy="aluno", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
