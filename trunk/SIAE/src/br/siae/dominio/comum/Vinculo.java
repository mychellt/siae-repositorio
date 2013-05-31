package br.siae.dominio.comum;

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
import br.siae.arq.dominio.Usuario;



@Entity
@Table(name="vinculo", schema="comum")
public class Vinculo implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_VINCULO")
	@SequenceGenerator(name="SEQ_VINCULO", sequenceName="comum.seq_vinculo", allocationSize=1)
	@Column(name="id_vinculo")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_instituicao", nullable=false)
	private Instituicao instituicao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
