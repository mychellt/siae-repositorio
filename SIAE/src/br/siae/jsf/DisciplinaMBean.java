package br.siae.jsf;

import java.util.ArrayList;
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
import br.siae.dominio.academico.Nivel;
import br.siae.service.DisciplinaService;

@Controller
@Scope("session")
public class DisciplinaMBean extends GenericController<Disciplina> implements ArqException{
	@Resource(name="disciplinaService")
	private DisciplinaService service;
	
	private Disciplina disciplina;
	
	
	public DisciplinaMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new Disciplina();
		obj.setNivel( new Nivel() );
	}
	
	public String iniciarCadastro() {
		resetObj();
		try {
			lista = (List<Disciplina>) service.getAll(Disciplina.class);
		} catch (Exception e) {
			addMensagemErro(processaException(e));
		}
		return getPaginaCadastro();
	}
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validar();
		if( isContemErros() ) {
			return null;
		}
		try {
			if( ValidatorUtil.isNotEmpty(obj)) {
				Disciplina ObjRemocao = lista.get( lista.indexOf(obj) );
				obj = service.executeCadastro(obj);
				lista.remove(ObjRemocao);
				lista.add(obj);
			}
			else {
				obj = service.executeCadastro(obj);
				lista.add(obj);
			}
			resetObj();
		} catch (Exception e) {
			obj.setId(0);
			addMensagemErro( processaException(e) );
		}
		addMensagemInformacao("Opera��o realizada com sucesso!");
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}

	public String remover(Disciplina disciplina) {
		disciplina = service.getByPrimaryKey(Disciplina.class, disciplina.getId() );
		if( ValidatorUtil.isEmpty(disciplina) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			disciplina = service.executeRemocao(disciplina);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
			return null;
		}
		
		lista.remove(disciplina);
		resetObj();
		return getPaginaCadastro();
	}

	private void validar() {
		if( ValidatorUtil.isEmpty(obj.getNome()) ) {
			addMensagemErro("Nome: campo obrigat�rio n�o informado");
		}
		if( ValidatorUtil.isEmpty(obj.getNivel()) ) {
			addMensagemErro("N�vel: campo obrigat�rio n�o informado");
		}
		if( ValidatorUtil.isEmpty(obj.getCargaHoraria())){
			addMensagemErro("Carga Hor�ria: campo obrigat�rio n�o informado");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "J� existe uma disciplina cadastrada com esse informa��es";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "O registro n�o pode ser removido pois existe uma associa��o com outros registros utilizados no sistema.";
		}
		return e.getMessage();
	}
	
	public Collection<Disciplina> getAll() {
		try {
			return service.getAll(Disciplina.class);
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		return new ArrayList<Disciplina>();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
