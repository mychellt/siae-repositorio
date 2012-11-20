package br.siae.dominio.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.siae.arq.dominio.Persistable;


@Entity
@Table(name="categoria", schema="rh")
public class Categoria implements Persistable{
	public static final int DOCENTE = 1;
	public static final int TECNICO_ADMINISTRATIVO = 2;
	public static final int NAO_ESPECIFICADO = 3;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CATEGORIA")
	@SequenceGenerator(name="SEQ_CATEGORIA", sequenceName="rh.seq_categoria", allocationSize=1)
	@Column(name="id_categoria", nullable=false)
	private long id;
	
	@Column(name="denominacao", nullable=false)
	private String denominacao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
}
