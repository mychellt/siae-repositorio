package br.siae.jsf;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Professor;
import br.siae.service.TurmaService;

@Controller
@Scope("session")
public class TurmaMBean extends AbstractSiaeController<Turma> implements ArqException{
	private ProfessorDataModel professores;
	private Professor[] professoresSelecionados;
	
	@Resource(name="turmaService")
	private TurmaService service;
	
	
	public TurmaMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
		obj.setNome( new String() );
	}
	
	public String iniciarCadastro() {
		resetObj();
		setConfirmButton("Cadastrar");
		try {
			professores = new ProfessorDataModel();
			professores.setWrappedData( service.getAll(Professor.class) );

		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			service.executeCadastro(obj, Arrays.asList(professoresSelecionados) );
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
		if( ValidatorUtil.isEmpty( Arrays.asList(professoresSelecionados) ) ){
			addMensagemErro("Selecione pelo menos um professor para a turma");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}

	public ProfessorDataModel getProfessores() {
		return professores;
	}

	public void setProfessores(ProfessorDataModel professores) {
		this.professores = professores;
	}
	
	public Professor[] getProfessoresSelecionados() {
		return professoresSelecionados;
	}

	public void setProfessoresSelecionados(Professor[] professoresSelecionados) {
		this.professoresSelecionados = professoresSelecionados;
	}

}