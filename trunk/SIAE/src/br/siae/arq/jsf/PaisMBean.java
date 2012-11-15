package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Pais;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.PaisService;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class PaisMBean extends AbstractSiaeController<Pais> implements ArqException{
	@Resource(name="paisService")
	private PaisService service;
	
	public Collection<Pais> getAll() {
		try {
			if( ValidatorUtil.isEmpty(lista) ) {
				lista = (List<Pais>) service.getAll(Pais.class);				
			}
			return lista;
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Pais>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return "Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.";
	} 
}
