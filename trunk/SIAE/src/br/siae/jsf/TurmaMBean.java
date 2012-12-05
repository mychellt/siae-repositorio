package br.siae.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.DisciplinaTurmaProfessor;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Professor;
import br.siae.service.TurmaProfessorService;
import br.siae.service.TurmaService;

@Controller
@Scope("session")
public class TurmaMBean extends AbstractSiaeController<Turma> implements ArqException{
	private TurmaProfessor turmaProfessor;
	private DisciplinaDataModel disciplinas;
	private Disciplina[] disciplinasSelecionadas;
	private Collection<TurmaProfessor> professores;
	private boolean alteracaoAssociacaoProfessor;
	
	@Resource(name="turmaService")
	private TurmaService service;
	
	@Resource(name="turmaProfessorService")
	private TurmaProfessorService tpService;
	
	
	public TurmaMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
		obj.setNome( new String() );
	}
	
	public String iniciarListagem() {
		try {
			lista = (List<Turma>) service.getAll(Turma.class);
			turmaProfessor = new TurmaProfessor();
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		return getPaginaListagem();
	}
	
	public String iniciarCadastro() {
		try {
			resetObj();
			
			turmaProfessor = new TurmaProfessor();
			turmaProfessor.setProfessor( new Professor() );
			turmaProfessor.setTurma( new Turma() );
			turmaProfessor.setDisciplinas( new ArrayList<DisciplinaTurmaProfessor>() );
			
			professores = new ArrayList<TurmaProfessor>();
			disciplinasSelecionadas = null;
			disciplinas = new DisciplinaDataModel();
			disciplinas.setWrappedData( service.getAll(Disciplina.class) );
			
			alteracaoAssociacaoProfessor = false;
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		
		try {
			disciplinas = new DisciplinaDataModel();
			disciplinas.setWrappedData( service.getAll(Disciplina.class) );
			professores = tpService.getByTurma(obj);
			for( TurmaProfessor tp : professores ) {
				tp.setDisciplinas( tpService.getByExactField(DisciplinaTurmaProfessor.class, "turmaProfessor.id", tp.getId() ) );
			}
			obj.setProfessores( professores );
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String preAlterarProfessor() {
		ArrayList<Disciplina> lista = new ArrayList<Disciplina>();
		for( DisciplinaTurmaProfessor dtp : turmaProfessor.getDisciplinas() ) {
			lista.add(dtp.getDisciplina());
		}
		disciplinasSelecionadas = lista.toArray( disciplinasSelecionadas );	
		alteracaoAssociacaoProfessor = false;
		return getPaginaCadastro();
	}
	
	public String removerProfessor() {
		return getPaginaCadastro();
	}
	
	public String inserirProfessor() {
		validateInserirProfessor();
		
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		if( isAlteracaoAssociacaoProfessor()  ) {
			
		}
		for( Disciplina disciplina : disciplinasSelecionadas ) {
			DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();
			dtp.setDisciplina(disciplina);
			dtp.setTurmaProfessor(turmaProfessor);
			if( ValidatorUtil.isEmpty( turmaProfessor.getDisciplinas() ) ) {
				turmaProfessor.setDisciplinas( new ArrayList<DisciplinaTurmaProfessor>() ); 
			}
			turmaProfessor.getDisciplinas().add(dtp);
		}
		if( ValidatorUtil.isEmpty(professores) ) {
			professores = new ArrayList<TurmaProfessor>();
		}
		
		professores.add(turmaProfessor);
		disciplinasSelecionadas = null;
		alteracaoAssociacaoProfessor = false;
		return getPaginaCadastro();
	}

	private void validateInserirProfessor() {
		if( ValidatorUtil.isEmpty(turmaProfessor.getProfessor()) ) {
			addMensagemErro("Professor: campo obrigatório não informado.");
		}
		List<Disciplina> disciplinas = Arrays.asList(disciplinasSelecionadas);
		if( ValidatorUtil.isEmpty(disciplinas) ) {
			addMensagemErro("Selecione pelo menos uma disciplinas para associar o professor a turma");
		}
		//Verificar se o professor já foi associado à turma.
		if( professores.contains(turmaProfessor.getProfessor()) ) {
			addMensagemErro("O professor já está associado à turma");
		}
	}
	
	public String cadastrar() {
		validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			obj.setProfessores( new ArrayList<TurmaProfessor>() );
			for( TurmaProfessor tp : professores ) {
				obj.getProfessores().add(tp);
			}
			service.executeCadastro(obj);
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
			return getPaginaCadastro();
		}
		
		return getPaginaComprovante();
	}
	

	private void validate() {
		if( ValidatorUtil.isEmpty( obj.getAno() ) ) {
			addMensagemErro("Ano: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Nome: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getSerie() ) ) {
			addMensagemErro("Série: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getTurno() ) ) {
			addMensagemErro("Turno: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getNumeroVagas() ) ) {
			addMensagemErro("Número de Vagas: campo obrigatório não informado");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}

	public DisciplinaDataModel getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(DisciplinaDataModel disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Disciplina[] getDisciplinasSelecionadas() {
		return disciplinasSelecionadas;
	}

	public void setDisciplinasSelecionadas(Disciplina[] disciplinasSelecionadas) {
		this.disciplinasSelecionadas = disciplinasSelecionadas;
	}

	public Collection<TurmaProfessor> getProfessores() {
		return professores;
	}

	public void setProfessores(Collection<TurmaProfessor> professores) {
		this.professores = professores;
	}

	public TurmaProfessor getTurmaProfessor() {
		return turmaProfessor;
	}

	public void setTurmaProfessor(TurmaProfessor turmaProfessor) {
		this.turmaProfessor = turmaProfessor;
	}

	public boolean isAlteracaoAssociacaoProfessor() {
		return alteracaoAssociacaoProfessor;
	}

	public void setAlteracaoAssociacaoProfessor(boolean alteracaoAssociacaoProfessor) {
		this.alteracaoAssociacaoProfessor = alteracaoAssociacaoProfessor;
	}
	
}