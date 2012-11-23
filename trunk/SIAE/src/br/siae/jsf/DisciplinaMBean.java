package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.Nivel;
import br.siae.service.DisciplinaService;

@Controller
@Scope("session")
public class DisciplinaMBean extends AbstractSiaeController<Disciplina> implements ArqException{
	@Resource(name="disciplinaService")
	private DisciplinaService service;
	
	
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
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
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
		addMensagemInformacao("Operação realizada com sucesso!");
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}

	public String remover() {
		obj = service.getByPrimaryKey(Disciplina.class, getParameterInt("idDisciplina") );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = service.executeRemocao(obj);
			lista.remove(obj);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
		}
		
		resetObj();
		return getPaginaCadastro();
	}

	private void validar() {
		if( ValidatorUtil.isEmpty(obj.getNome()) ) {
			addMensagemErro("Nome: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty(obj.getNivel()) ) {
			addMensagemErro("Nível: campo obrigatório não informado");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma disciplina cadastrada com esse informações";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
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

}
