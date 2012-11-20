package br.siae.dominio.comum;

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
import javax.persistence.UniqueConstraint;

import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Persistable;
import br.siae.dominio.academico.Nivel;


@Entity
@Table(name="instituicao", schema="comum", uniqueConstraints={@UniqueConstraint(columnNames={"codigo_inep"})})
public class Instituicao implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_INSTITUICAO")
	@SequenceGenerator(name="SEQ_INSTITUICAO", sequenceName="comum.seq_instituicao", allocationSize=1)
	@Column(name="id_instituicao")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco", insertable=true, nullable=false, updatable=true)
	private Endereco endereco;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_modalidade", nullable=false)
	private Nivel modalidade;
	
	@Column(name="email")
	private String email;
	
	@Column(name="codigo_inep", nullable=false, unique=true)
	private String codigoInep;

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

	public String getCodigoInep() {
		return codigoInep;
	}

	public void setCodigoInep(String codigoInep) {
		this.codigoInep = codigoInep;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Nivel getModalidade() {
		return modalidade;
	}

	public void setModalidade(Nivel modalidade) {
		this.modalidade = modalidade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNomeCodigo() {
		return getNome() + " (" + getCodigoInep() + ")";
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
		Instituicao other = (Instituicao) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
