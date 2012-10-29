package br.siae.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.PessoaDAO;
import br.siae.arq.dominio.EspeciePessoa;
import br.siae.arq.dominio.Identidade;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.jsf.AbstractController;
import br.siae.arq.jsf.AbstractCrudController;

@Controller
@Scope("session")
public class ConsultadorPessoaMBean extends AbstractCrudController<Pessoa>{
	private AbstractController controlador;
	
	/** Realiza a consulta por nome.*/
	private boolean byNome;
	
	/** Realiza a consulta por CPF.*/
	private boolean byCpf;
	
	/** Realizar a consulta por matrícula.*/
	private boolean byMatricula;
	
	/** Realizar a consulta por RG.*/
	private boolean byRg;
	
	/** Realizar a consulta por Número do Registro de Nascimento.*/
	private boolean byRegistroNascimento;
	
	/** Realizar a consulta por nome da mãe*/
	private boolean byNomeMae;
	
	/** Realiza a consulta por nome do pai.*/
	private boolean byNomePai;
	
	/** Realiza a busca por data de nascimento */
	private boolean byDataNascimento;
	
	/** Dao para realização da consulta.*/
	@Resource(name="pessoaDAO")
	private PessoaDAO dao;
	
	public ConsultadorPessoaMBean() {
		obj = new Pessoa();
		obj.setIdentidade( new Identidade() );
		lista = new ArrayList<Pessoa>();
	}
	
	private void validate() {
		if( !isByCpf() &&
			!isByMatricula() && 
			!isByNome() && 
			!isByNomeMae() && 
			!isByNomePai() && 
			!isByRegistroNascimento() && 
			!isByRg() &&
			!isByDataNascimento() ) {
				addMensagemErro("Selecione pelo menos um critério para realizar busca.");
			}
	}
	
	public String consultar( ) {
		validate();
		
		if( !isContemErros() ) {
			try {
				lista = ((List<Pessoa>) dao.findByCriterios( getEspeciePessoa(),
										 	 isByNome() ? obj.getNome() : null,
										 	 isByNomeMae() ? obj.getNomeMae() : null, 
										 	 isByNomePai() ? obj.getNomePai() : null,
										 	 isByCpf() ? obj.getCpf() : null,
										 	 isByDataNascimento() ? obj.getDataNascimento() : null,		 
										 	 isByRegistroNascimento() ? obj.getCertidaoNascimento().getNumero() : null,
										 	 isByRg() ? obj.getIdentidade().getNumero() : null ));
			} catch (DAOException e) {
				addMensagemErro("Houve um erro ao tentar recupear os registros, por favor entre em contato com o administrador do sistema.");
				e.printStackTrace();
			}
		}
		return null;
	}
	public boolean isByNome() {
		return byNome;
	}
	public void setByNome(boolean byNome) {
		this.byNome = byNome;
	}
	public boolean isByCpf() {
		return byCpf;
	}
	public void setByCpf(boolean byCpf) {
		this.byCpf = byCpf;
	}
	public boolean isByMatricula() {
		return byMatricula;
	}
	public void setByMatricula(boolean byMatricula) {
		this.byMatricula = byMatricula;
	}
	public boolean isByRg() {
		return byRg;
	}
	public void setByRg(boolean byRg) {
		this.byRg = byRg;
	}
	public boolean isByRegistroNascimento() {
		return byRegistroNascimento;
	}
	public void setByRegistroNascimento(boolean byRegistroNascimento) {
		this.byRegistroNascimento = byRegistroNascimento;
	}
	public boolean isByNomeMae() {
		return byNomeMae;
	}
	public void setByNomeMae(boolean byNomeMae) {
		this.byNomeMae = byNomeMae;
	}
	public boolean isByNomePai() {
		return byNomePai;
	}
	public void setByNomePai(boolean byNomePai) {
		this.byNomePai = byNomePai;
	}

	public boolean isByDataNascimento() {
		return byDataNascimento;
	}

	public void setByDataNascimento(boolean byDataNascimento) {
		this.byDataNascimento = byDataNascimento;
	}

	public PessoaDAO getDao() {
		return dao;
	}

	public void setDao(PessoaDAO dao) {
		this.dao = dao;
	}

	public EspeciePessoa getEspeciePessoa() {
		if( isConsultaAluno() ) return new EspeciePessoa( EspeciePessoa.ALUNO );
		else if( isConsultaFuncionario() ) return new EspeciePessoa( EspeciePessoa.PROFESSOR );
		else if( isConsultaFuncionario() ) return new EspeciePessoa( EspeciePessoa.FUNCIONARIO );
		else return new EspeciePessoa();
	}
	
	public boolean isConsultaAluno() {
		return controlador instanceof AlunoMBean;
	}
	public boolean isConsultaProfessor() {
		return controlador instanceof ProfessorMBean;
	}
	public boolean isConsultaFuncionario() {
		return controlador instanceof FuncionarioMBean;
	}

	public AbstractController getControlador() {
		return controlador;
	}

	public void setControlador(AbstractController controlador) {
		this.controlador = controlador;
	}
}
