package br.siae.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.dominio.comum.Turno;

@Controller
@Scope("request")
public class TurnoMBean {
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public TurnoMBean() { }
	public Collection<Turno> getAll( ) {
		return dao.findAll( Turno.class);
	}
		
}
