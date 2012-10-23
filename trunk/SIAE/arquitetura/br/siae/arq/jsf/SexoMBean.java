package br.siae.arq.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Sexo;

@Controller
@Scope("request")
public class SexoMBean {
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Collection<Sexo> getAll( ) {
		Collection<Sexo> lista = dao.findAll( Sexo.class );
		return lista;
	}
}
