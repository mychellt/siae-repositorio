package br.siae.dominio.academico;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;


@Entity
@Table(name="requerimento_matricula", schema="academico")
public class RequerimentoMatricula implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_REQUERIMENTO_MATRICULA")
	@SequenceGenerator(name="SEQ_REQUERIMENTO_MATRICULA", sequenceName="academico.seq_requerimento_matricula", allocationSize=1)
	@Column(name="id_requerimento_matricula")
	private long id;
	
	@Column(name="numero_matricula")
	private long numeroMatricula;
	
	@OneToMany(mappedBy="requerimentoMatricula", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Collection<ItemRequerimentoMatricula> itens;

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
}
