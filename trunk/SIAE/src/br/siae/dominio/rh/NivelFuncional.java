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
import javax.persistence.UniqueConstraint;

import br.siae.arq.dominio.Persistable;

@Entity
@Table(name="nivel_funcional", schema="rh",uniqueConstraints={@UniqueConstraint(columnNames={"denominacao"})})
public class NivelFuncional  implements Persistable{
	public static final int AUXILIAR = 1;
	public static final int INTERMEDIARIO  = 2;
	public static final int MEDIO = 3;
	public static final int SUPERIOR = 4;
	public static final int NAO_INFORMADO = 5;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_NIVEL_FUNCIONAL")
	@SequenceGenerator(name="SEQ_NIVEL_FUNCIONAL", sequenceName="rh.seq_nivel_funcional", allocationSize=1)
	@Column(name="id_nivel_funcional")
	private long id;
	
	@Column(name="denominacao")
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
		NivelFuncional other = (NivelFuncional) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
