package br.siae.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.negocio.ProcessadorSerie;

@Controller
@Scope("session")
public class SerieMBean extends AbstractCrudController<Serie>{
	
	@Resource(name="processadorSerie")
	private ProcessadorSerie processador;
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public SerieMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Serie();
		obj.setNivel( new Nivel() );
	}
	
	public String preAlterar() {
		obj =  dao.findByPrimaryKey( Serie.class, obj.getId() );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() throws DAOException {
		validar();
		if( isContemErros() ) {
			return null;
		}
		try {
			if( ValidatorUtil.isNotEmpty(obj)) {
				lista.remove(obj);
				obj = processador.executarCadastro(obj);
				addMensagemInformacao("Permiss�o alterada com sucesso!");
				lista.add(obj);
			}
			else {
				obj = processador.executarCadastro(obj);
				lista.add(obj);
				addMensagemInformacao("Permiss�o cadastrada com sucesso!");
			}
			resetObj();
		} catch (NegocioException e) {
			addMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}

	@Override
	public String iniciarCadastro() {
		resetObj();
		lista = (List<Serie>) dao.findAll(Serie.class);
		return super.iniciarCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Denomina��o: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNivel() ) ) {
			addMensagemErro("N�vel: campo obrigat�rio n�o informado.");
		}
	}
	
	public String remover() throws DAOException {
		obj =  dao.findByPrimaryKey( Serie.class, obj.getId() );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = (Serie) processador.remover(obj);
			lista.remove(obj);
		}
		catch(NegocioException e) {
			addMensagemErro( e.getMessage() );
		}
		return getPaginaCadastro();
	}
}