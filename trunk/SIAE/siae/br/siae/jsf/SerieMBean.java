package br.siae.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.service.SerieService;

@Controller
@Scope("session")
public class SerieMBean extends AbstractSiaeController<Serie>{
	
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
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
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
				obj = serieService.executeCadastro(obj);
				addMensagemInformacao("Série alterada com sucesso!");
				lista.add(obj);
			}
			else {
				obj = serieService.executeCadastro(obj);
				lista.add(obj);
				addMensagemInformacao("Série cadastrada com sucesso!");
			}
		} catch (NegocioException e) {
			addMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
		resetObj();
		setConfirmButton("Cadastrar");
		return null;
	}

	public String iniciarCadastro() {
		resetObj();
		try {
			lista = (List<Serie>) serieService.getAll(Serie.class);
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return getPaginaCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Denominação: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNivel() ) ) {
			addMensagemErro("Nível: campo obrigatório não informado.");
		}
	}
	
	public String remover() throws DAOException {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = (Serie) serieService.executeRemocao(obj);
			lista.remove(obj);
		}
		catch(NegocioException e) {
			addMensagemErro( e.getMessage() );
		}
		return getPaginaCadastro();
	}
}
