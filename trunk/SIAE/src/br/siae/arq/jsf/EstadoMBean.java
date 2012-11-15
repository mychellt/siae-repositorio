package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Estado;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.EstadoService;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class EstadoMBean extends AbstractSiaeController<Estado> implements ArqException{
	
	@Resource(name="estadoService")
	private EstadoService service;
	
	public Collection<Estado> getAll() {
		try {
			if( ValidatorUtil.isEmpty(lista) ){
				lista = (List<Estado>) service.getAll(Estado.class); 
			}
			return lista;
		} catch (Exception e) {
			processaException(e);
		}
		return new ArrayList<Estado>();
	}

	@Override
	public String processaException(Exception e) {
		return null;
	} 
}
