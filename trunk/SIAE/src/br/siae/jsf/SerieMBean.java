package br.siae.jsf;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.service.SerieService;

@Controller
@Scope("session")
public class SerieMBean extends AbstractSiaeController<Serie> implements ArqException{
	
	@Resource(name="serieService")
	private SerieService serieService;
	
	public SerieMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Serie();
		obj.setNivel( new Nivel() );
	}
	
	public String preAlterar() {
		obj = serieService.getByPrimaryKey(Serie.class, getParameterInt("idSerie") );
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
				lista.remove(obj);
				obj = serieService.executeCadastro(obj);
				addMensagemInformacao("S�rie alterada com sucesso!");
				lista.add(obj);
			}
			else {
				obj = serieService.executeCadastro(obj);
				lista.add(obj);
				addMensagemInformacao("S�rie cadastrada com sucesso!");
			}
			resetObj();
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}

	public String iniciarCadastro() {
		resetObj();
		try {
			lista = (List<Serie>) serieService.getAll(Serie.class);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Denomina��o: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNivel() ) ) {
			addMensagemErro("N�vel: campo obrigat�rio n�o informado.");
		}
	}
	
	public String remover() {
		obj = serieService.getByPrimaryKey(Serie.class, getParameterInt("idSerie") );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = (Serie) serieService.executeRemocao(obj);
			lista.remove(obj);
		}
		catch(NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}

	@Override
	public String processaException(Exception e) {
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "J� existe um S�rie cadastrada com essa denomina��o para esse Ni�vel.";
		}
		return e.getMessage();
	}
	
	public Collection<Serie> getAll() {
		return ArqCache.getSeries();
	}
}
