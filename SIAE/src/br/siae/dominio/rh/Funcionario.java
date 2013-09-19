package br.siae.dominio.rh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.arq.dao.Persistent;
import br.arq.dominio.Pessoa;
import br.arq.seguranca.Usuario;
import br.siae.dominio.comum.Instituicao;


@Entity
@Table(name="funcionario", schema="rh")
public class Funcionario implements Persistent{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_FUNCIONARIO")
	@SequenceGenerator(name="SEQ_FUNCIONARIO", sequenceName="rh.seq_funcionario", allocationSize=1)
	@Column(name="id_funcionario", nullable=false)
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false)
	private Pessoa pessoa;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_lotacao", nullable=false)
	private Instituicao lotacao;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", nullable=true)
	private Usuario usuario;

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

	public Instituicao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Instituicao lotacao) {
		this.lotacao = lotacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
