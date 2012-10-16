package br.siae.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.dominio.academico.Nivel;

@Controller
@Scope("request")
public class NivelMBean {
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public NivelMBean() { }
	public Collection<Nivel> getAll( ) {
		return dao.findAll( Nivel.class);
	}
			
}
