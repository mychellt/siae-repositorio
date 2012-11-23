package br.siae.dominio.academico;

import java.math.BigDecimal;

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



@Entity
@Table(name="disciplina", schema="academico")
public class Disciplina implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_DISCIPLINA")
	@SequenceGenerator(name="SEQ_DISCIPLINA", sequenceName="academico.seq_disciplina", allocationSize=1)
	@Column(name="id_disciplina", nullable=false)
	private long id;

	@Column(name="nome", nullable=false)
	private String nome;
	
	@Column(name="sigla", nullable=true)
	private String sigla;
	
	@Column(name="reprova", nullable=false)
	private boolean reprova;
	
	@Column(name="suplementar", nullable=true)
	private boolean suplementar;
	
	@Column(name="valor_dependencia", nullable=true)
	private BigDecimal valorDependencia;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel", nullable=false)
	private Nivel nivel;
	
	@Column(name="carga_horaria")
	private int cargaHoraria;
	
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

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
