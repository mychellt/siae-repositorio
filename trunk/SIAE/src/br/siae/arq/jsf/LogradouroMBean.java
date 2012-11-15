package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.siae.arq.dominio.Logradouro;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.LogradouroService;
import br.siae.arq.utils.ValidatorUtil;

@Component
@Scope("session")
public class LogradouroMBean extends AbstractSiaeController<Logradouro> implements ArqException{
	
	@Resource(name="logradouroService")
	private LogradouroService service;
	
	public Collection<Logradouro> getAll() {
		try {
			if( ValidatorUtil.isEmpty(lista)){
				lista = (List<Logradouro>) service.getAll(Logradouro.class); 
			}
			return lista;
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Logradouro>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return "Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.";
	}
}
