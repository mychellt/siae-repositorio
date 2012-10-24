package br.siae.arq.dominio;

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

/**
 * Classe de domínio para representação de país.
 * <br/>
 * @author Mychell Teixeira.
 *
 */
@Entity
@Table(name="pais", schema="comum")
public class Pais implements Persistable{
	/** Identificador do objeto */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PAIS")
	@SequenceGenerator(name="SEQ_PAIS", sequenceName="seq_pais", allocationSize=1)
	@Column(name="id_pais")
	private long id;
	
	/** Nome do país*/
	@Column(name="nome")
	private String nome;
	
	/** Descrição da nacionalidade para nascidos/nacionaliados no país.*/
	@Column(name="descricao_nascionalidade")
	private String descricaoNacionalidade;
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="pais", fetch=FetchType.LAZY)
	private Collection<Estado> estados;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getDescricaoNacionalidade() {
		return descricaoNacionalidade;
	}

	public void setDescricaoNacionalidade(String descricaoNacionalidade) {
		this.descricaoNacionalidade = descricaoNacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Estado> getEstados() {
		return estados;
	}

	public void setEstados(Collection<Estado> estados) {
		this.estados = estados;
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
		Pais other = (Pais) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
