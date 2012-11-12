package br.siae.dominio.academico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;



@Entity
@Table(name="disciplina", schema="academico")
public class Disciplina implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA")
	@SequenceGenerator(name="SEQ_DISCIPLINA", sequenceName="academico.seq_disciplina", allocationSize=1)
	@Column(name="id_disciplina")
	private long id;

	@Column(name="nome")
	private String nome;
	
	@Column(name="sigla")
	private String sigla;
	
	@Column(name="reprova")
	private boolean reprova;
	
	@Column(name="suplementar")
	private boolean suplementar;
	
	@Column(name="valor_dependencia")
	private BigDecimal valorDependencia;
	
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isReprova() {
		return reprova;
	}

	public void setReprova(boolean reprova) {
		this.reprova = reprova;
	}

	public boolean isSuplementar() {
		return suplementar;
	}

	public void setSuplementar(boolean suplementar) {
		this.suplementar = suplementar;
	}

	public BigDecimal getValorDependencia() {
		return valorDependencia;
	}

	public void setValorDependencia(BigDecimal valorDependencia) {
		this.valorDependencia = valorDependencia;
	}
}
