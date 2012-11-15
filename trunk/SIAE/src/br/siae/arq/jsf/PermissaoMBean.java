package br.siae.arq.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.service.PermissaoService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;


@Controller
@Scope("session")
public class PermissaoMBean extends AbstractSiaeController<Permissao> implements ArqException{	
	@Resource(name="permissaoService")
	private PermissaoService permissaoService;
	
	public PermissaoMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new Permissao();
	}
	
	public String iniciarCadastro()  {
		resetObj();
		try {
			lista = (List<Permissao>) permissaoService.getAll(Permissao.class);
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Denomina��o: campo obrigat�rio n�o informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getDescricao() ) ) {
			addMensagemErro("Descri��o: campo obrigat�rio n�o informado.");
		}
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
				Permissao ObjRemocao = lista.get( lista.indexOf(obj) );
				obj = permissaoService.executeCadastro(obj);
				addMensagemInformacao("Permiss�o alterada com sucesso!");
				lista.remove(ObjRemocao);
				lista.add(obj);
			}
			else {
				obj = permissaoService.executeCadastro(obj);
				lista.add(obj);
				addMensagemInformacao("Permiss�o cadastrada com sucesso!");
			}
			resetObj();
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	public String remover() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando n�o se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = permissaoService.executeRemocao(obj);
			lista.remove(obj);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
		}
		
		resetObj();
		return getPaginaCadastro();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "J� existe uma Permiss�o cadastrar com essa denomina��o";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}
	
}
