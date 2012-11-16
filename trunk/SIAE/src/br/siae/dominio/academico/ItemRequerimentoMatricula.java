package br.siae.dominio.academico;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="item_requerimento_matricula", schema="academico")
public class ItemRequerimentoMatricula implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_ITEM_REQUERIMENTO_MATRICULA")
	@SequenceGenerator(name="SEQ_ITEM_REQUERIMENTO_MATRICULA", sequenceName="academico.seq_item_requerimento_matricula", allocationSize=1)
	@Column(name="id_item_requerimento_matricula")
	private  long id;
	
	@Column(name="ano")
	private int ano;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_turma", insertable=true)
	private Turma turma;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data")
	private Date data;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_requerimento_matricula", insertable=true)
	private RequerimentoMatricula requerimentoMatricula;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public RequerimentoMatricula getRequerimentoMatricula() {
		return requerimentoMatricula;
	}

	public void setRequerimentoMatricula(RequerimentoMatricula requerimentoMatricula) {
		this.requerimentoMatricula = requerimentoMatricula;
	}
}
