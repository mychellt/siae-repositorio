package br.siae.dominio.academico;

import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.arq.dao.Persistent;
import br.arq.seguranca.Usuario;


@Entity
@Table(name="requerimento_matricula", schema="academico")
public class RequerimentoMatricula implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_REQUERIMENTO_MATRICULA")
	@SequenceGenerator(name="SEQ_REQUERIMENTO_MATRICULA", sequenceName="academico.seq_requerimento_matricula", allocationSize=1)
	@Column(name="id_requerimento_matricula")
	private long id;
	
	@Column(name="numero_matricula")
	private long numeroMatricula;
	
	@OneToMany(mappedBy="requerimentoMatricula", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Collection<ItemRequerimentoMatricula> itens;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_responsavel_cadastro", insertable=true)
	private Usuario responsavelCadastro;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_aluno", nullable=false)
	private Aluno aluno;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(long numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Collection<ItemRequerimentoMatricula> getItens() {
		return itens;
	}

	public void setItens(Collection<ItemRequerimentoMatricula> itens) {
		this.itens = itens;
	}

	public Usuario getResponsavelCadastro() {
		return responsavelCadastro;
	}

	public void setResponsavelCadastro(Usuario responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
