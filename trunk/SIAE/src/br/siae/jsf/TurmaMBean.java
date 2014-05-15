package br.siae.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.erros.NegocioException;
import br.arq.jsf.GenericController;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
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
public class TurmaMBean extends GenericController<Turma> implements ArqException{
	private TurmaProfessor turmaProfessor;
	private DisciplinaDataModel disciplinas;
	private Disciplina[] disciplinasSelecionadas;
	private boolean alteracaoAssociacaoProfessor;
	
	@Resource(name="turmaService")
	private TurmaService service;
	
	@Resource(name="turmaProfessorService")
	private TurmaProfessorService tpService;
	
	private Turma turma;
	
	
	public TurmaMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
		obj.setNome( new String() );
		obj.setProfessores( new ArrayList<TurmaProfessor>() );
		obj.setProfessoresRemocao( new ArrayList<TurmaProfessor>() );
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
			obj.setProfessores( tpService.getByTurma(obj) );
			obj.setProfessoresRemocao( new ArrayList<TurmaProfessor>() );
			for( TurmaProfessor tp : obj.getProfessores() ) {
				tp.setDisciplinas( tpService.getByExactField(DisciplinaTurmaProfessor.class, "turmaProfessor.id", tp.getId() ) );
			}
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
		alteracaoAssociacaoProfessor = true;
		return getPaginaCadastro();
	}
	
	public String removerProfessor() {
		obj.getProfessoresRemocao().add(turmaProfessor);
		obj.getProfessores().remove(turmaProfessor);
		turmaProfessor = new TurmaProfessor();
		turmaProfessor.setDisciplinas( new ArrayList<DisciplinaTurmaProfessor>() );
		turmaProfessor.setProfessor( new Professor() );
		turmaProfessor.setTurma( new Turma() );
		turmaProfessor.setDisciplinasRemoacao( new ArrayList<DisciplinaTurmaProfessor>() );
		disciplinasSelecionadas = null;
		return getPaginaCadastro();
	}
	
	public String inserirProfessor() {
		
		validateInserirProfessor();
		
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		TurmaProfessor tp = new TurmaProfessor();
		try {
			tp = (TurmaProfessor) turmaProfessor.clone();
		} catch (CloneNotSupportedException e) {
			addMensagemErro(processaException(e));
		}
		
		if( isAlteracaoAssociacaoProfessor() ) {
			if( ValidatorUtil.isEmpty( tp.getDisciplinasRemoacao() ) ) {
				tp.setDisciplinasRemoacao( new ArrayList<DisciplinaTurmaProfessor>() );
			}
			//Verifica se existem disciplinas a serem removidas
			Collection<DisciplinaTurmaProfessor> disciplinasAnteriores =  new ArrayList<DisciplinaTurmaProfessor>( tp.getDisciplinas() );
			Collection<Disciplina> disciplinas = new ArrayList<Disciplina>();
			for( DisciplinaTurmaProfessor dtp : disciplinasAnteriores ) {
				List<Disciplina> asList = Arrays.asList(disciplinasSelecionadas);
				if( !asList.contains(dtp.getDisciplina() ) ) {
					tp.getDisciplinasRemoacao().add(dtp);
					tp.getDisciplinas().remove(dtp);
				}
				disciplinas.add(dtp.getDisciplina());
			}
			//Verifica disciplinas adicionadas
			for( Disciplina disciplina : Arrays.asList(disciplinasSelecionadas) ) {
				if( !disciplinas.contains(disciplina) ) {
					DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();
					dtp.setDisciplina(disciplina);
					dtp.setTurmaProfessor(tp);
					tp.getDisciplinas().add(dtp);
				}
			}
			
			obj.getProfessores().remove(turmaProfessor);
			obj.getProfessores().add(tp);
			

		}
		else {
			for( Disciplina disciplina : disciplinasSelecionadas ) {
				DisciplinaTurmaProfessor dtp = new DisciplinaTurmaProfessor();
				dtp.setDisciplina(disciplina);
				dtp.setTurmaProfessor(tp);
				if( ValidatorUtil.isEmpty( tp.getDisciplinas() ) ) {
					tp.setDisciplinas( new ArrayList<DisciplinaTurmaProfessor>() ); 
				}
				tp.getDisciplinas().add(dtp);
			}
			tp.setTurma(obj);
			obj.getProfessores().add(tp);
		}
		
		turmaProfessor = new TurmaProfessor();
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
		if( obj.getProfessores().contains(turmaProfessor.getProfessor()) ) {
			addMensagemErro("O professor já está associado à turma");
		}
	}
	
	
	public String remover( Turma turma )  {
		turma = service.getByPrimaryKey(Turma.class, turma.getId());
		if( ValidatorUtil.isEmpty(turma) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			turma = (Turma) service.executeRemocao(turma);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
			return null;
		}
		lista.remove(turma);
		addMensagemInformacao("Operação realizada com sucesso!");
		resetObj();
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			service.executeCadastro(obj);
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
			return getPaginaCadastro();
		}
		
		addMensagemInformacao("Operação realizada com sucesso!");
		return getPaginaComprovanteCadastro();
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

	
	public Collection<Turma> getAll() {
		try {
			return service.getAll(Turma.class);
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Turma>();
	}
	
	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma disciplina cadastrada com esse informações";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "O registro não pode ser removido pois existe uma associação com outros registros utilizados no sistema.";
		}
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

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
}