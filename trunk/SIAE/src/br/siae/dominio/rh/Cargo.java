package br.siae.dominio.rh;

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
@Table(name="cargo", schema="rh")
public class Cargo implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CARGO")
	@SequenceGenerator(name="SEQ_CARGO", sequenceName="rh.seq_cargo", allocationSize=1)
	@Column(name="id_cargo")
	private long id;
	
	@Column(name="denominacao")
	private String denominacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_categoria", nullable=false)
	private Categoria categoria;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_nivel_funcional", nullable=false)
	private NivelFuncional nivelFuncional;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public NivelFuncional getNivelFuncional() {
		return nivelFuncional;
	}

	public void setNivelFuncional(NivelFuncional nivelFuncional) {
		this.nivelFuncional = nivelFuncional;
	}
}
