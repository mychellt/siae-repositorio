package br.siae.arq.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="certidao_nascimento", schema="comum")
public class CertidaoNascimento implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_CERTIDAO_NASCIMENTO")
	@SequenceGenerator(name="SEQ_CERTIDAO_NASCIMENTO", sequenceName="comum.seq_certidao_nascimento", allocationSize=1)
	@Column(name="id_certidao_nascimento")
	private long id;
	
	@Column(name="numero")
	private long numero;
	
	@Column(name="cartorio")
	private String cartorio;
	
	@Column(name="livro")
	private String livro;
	
	@Column(name="folha")
	private String folha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_expedicao")
	private Date dataExpedicao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getCartorio() {
		return cartorio;
	}

	public void setCartorio(String cartorio) {
		this.cartorio = cartorio;
	}

	public String getFolha() {
		return folha;
	}

	public void setFolha(String folha) {
		this.folha = folha;
	}

	public String getLivro() {
		return livro;
	}

	public void setLivro(String livro) {
		this.livro = livro;
	}

	public Date getDataExpedicao() {
		return dataExpedicao;
	}

	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
}
