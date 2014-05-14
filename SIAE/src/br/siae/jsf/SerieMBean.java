package br.siae.jsf;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.siae.jsf.AbstractSiaeController;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.service.SerieService;
import br.siae.utils.SIAECache;

@Controller
@Scope("session")
public class SerieMBean extends AbstractSiaeController<Serie> implements ArqException{
	
	@Resource(name="serieService")
	private SerieService serieService;
	
	private Serie serie;
	
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
	
	public String remover(Serie serie) {
		serie = serieService.getByPrimaryKey(Serie.class, serie.getId());
		if( ValidatorUtil.isEmpty(serie) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			serie = (Serie) serieService.executeRemocao(serie);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
			return null;
		}
		lista.remove(serie);
		addMensagemInformacao("Opera��o realizada com sucesso!");
		resetObj();
		return getPaginaCadastro();
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
	
	public Collection<Serie> getAll() {
		return SIAECache.getSeries();
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
}
