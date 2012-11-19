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

@Entity
@Table(name="formacao_academica", schema="academico")
public class FormacaoAcademica implements Persistable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_FORMACAO_ACADEMICA")
	@SequenceGenerator(name="SEQ_FORMACAO_ACADEMICA", sequenceName="academico.seq_formacao_academica", allocationSize=1)
	@Column(name="id_formacao_academica")
	private long id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel_formacao", insertable=true, nullable=false)
	private NivelFormacao nivel;
	
	@Column(name="curso")
	private String curso;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public NivelFormacao getNivel() {
		return nivel;
	}

	public void setNivel(NivelFormacao nivel) {
		this.nivel = nivel;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}
