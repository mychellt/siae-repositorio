package br.siae.dominio.rh;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.Usuario;
import br.siae.arq.jsf.converter.ConverterCPF;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.comum.Instituicao;

@Entity
@Table(name="professor", schema="rh")
public class Professor implements Persistable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator="SEQ_PROFESSOR")
	@SequenceGenerator(name="SEQ_PROFESSOR", sequenceName="rh.seq_professor", allocationSize=1)
	@Column(name="id_professor")
	private long id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pessoa", insertable=true, nullable=false)
	private Pessoa pessoa;
	
	@OneToMany(mappedBy="turma", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Collection<TurmaProfessor> turmas;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_usuario", insertable=true)
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_lotacao", nullable=false)
	private Instituicao lotacao;
	
	@Transient
	private List<Disciplina> disciplinas;
	

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<TurmaProfessor> getTurmas() {
		return turmas;
	}

	public void setTurmas(Collection<TurmaProfessor> turmas) {
		this.turmas = turmas;
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
		Professor other = (Professor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instituicao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Instituicao lotacao) {
		this.lotacao = lotacao;
	}
	
	public String getNomeExibicao( ) {
		if( ValidatorUtil.isEmpty(getPessoa() ) ) return "";
		return getPessoa().getNome() + " (CPF: " + ConverterCPF.formate( getPessoa().getCpf() ) + ")"; 
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
}
