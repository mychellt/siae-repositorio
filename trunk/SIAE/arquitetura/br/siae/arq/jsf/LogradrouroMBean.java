package br.siae.arq.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Logradouro;

@Component
@Scope("request")
public class LogradrouroMBean {
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public LogradrouroMBean() { }
	public Collection<Logradouro> getAll() {
		return dao.findAll( Logradouro.class );
	}
}
