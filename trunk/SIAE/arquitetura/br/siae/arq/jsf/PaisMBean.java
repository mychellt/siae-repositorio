package br.siae.arq.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Pais;

@Controller
@Scope("request")
public class PaisMBean {
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Collection<Pais> getAll() {
		return dao.findAll( Pais.class );
	} 
}
