package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.siae.arq.dominio.Logradouro;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.LogradouroService;

@Component
@Scope("request")
public class LogradouroMBean extends AbstractController{
	
	@Resource(name="logradouroService")
	private LogradouroService service;
	
	public Collection<Logradouro> getAll() {
		try {
			return service.getAll(Logradouro.class);
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Logradouro>();
	}
}
