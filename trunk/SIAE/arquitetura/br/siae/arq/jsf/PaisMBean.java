package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Pais;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.PaisService;

@Controller
@Scope("request")
public class PaisMBean extends AbstractController{
	@Resource(name="paisService")
	private PaisService service;
	
	public Collection<Pais> getAll() {
		try {
			return service.getAll(Pais.class);
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Pais>();
	} 
}
