package br.siae.dominio.rh;

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

import br.siae.arq.dominio.Persistable;
import br.siae.arq.dominio.Pessoa;


@Entity
@Table(name="professor", schema="rh")
public class Funcionario implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_FUNCIONARIO")
	@SequenceGenerator(name="SEQ_FUNCIONARIO", sequenceName="rh.seq_funcionario", allocationSize=1)
	@Column(name="id_funcionario")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false)
	private Pessoa pessoa;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String getNomeExibicao()  {
		return "Funcionário";
	}
}
