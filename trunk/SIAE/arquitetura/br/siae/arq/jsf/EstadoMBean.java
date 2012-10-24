package br.siae.arq.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Pais;

@Controller
@Scope("request")
public class EstadoMBean {
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Collection<Estado> getAll() {
		return dao.findAll( Estado.class );
	} 
}
