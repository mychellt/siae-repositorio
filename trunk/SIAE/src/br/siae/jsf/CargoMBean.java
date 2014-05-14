package br.siae.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.erros.NegocioException;
import br.arq.siae.jsf.AbstractSiaeController;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Cargo;
import br.siae.dominio.rh.Categoria;
import br.siae.dominio.rh.NivelFuncional;
import br.siae.service.CargoService;

@Controller
@Scope("session")
public class CargoMBean extends AbstractSiaeController<Cargo> implements ArqException {
	
	@Resource(name="cargoService")
	private CargoService service;
	
	private Cargo cargo;
	
	public CargoMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new Cargo();
		obj.setNivelFuncional( new NivelFuncional() );
		obj.setCategoria( new Categoria() );
	}
	
	public String iniciarCadastro()  {
		resetObj();
		try {
			lista = (List<Cargo>) service.getAll(Cargo.class);
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validar();
		if( isContemErros() ) {
			return null;
		}
		try {
			if( ValidatorUtil.isNotEmpty(obj)) {
				Cargo ObjRemocao = lista.get( lista.indexOf(obj) );
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
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Nome do Cargo: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNivelFuncional() ) ) {
			addMensagemErro("Nível Funcional: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getCategoria() ) ) {
			addMensagemErro("Categoria: campo obrigatório não informado.");
		}
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
	
	public String remover(Cargo cargo) {
		cargo = service.getByPrimaryKey(Cargo.class, cargo.getId() );
		if( ValidatorUtil.isEmpty(cargo) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			cargo = service.executeRemocao(cargo);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
		}		
		lista.remove(cargo);
		resetObj();
		addMensagemInformacao("Operação realizada com sucesso!");
		return getPaginaCadastro();
	}
	
	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe um cargo cadastrado com essas informações";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
}
