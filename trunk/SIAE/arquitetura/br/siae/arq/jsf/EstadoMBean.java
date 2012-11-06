package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Estado;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.EstadoService;

@Controller
@Scope("request")
public class EstadoMBean extends AbstractController{
	
	@Resource(name="estadoService")
	private EstadoService service;
	
	public Collection<Estado> getAll() {
		try {
			return service.getAll();
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Estado>();
	} 
}
