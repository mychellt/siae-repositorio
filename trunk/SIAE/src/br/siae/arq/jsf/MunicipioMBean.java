package br.siae.arq.jsf;

import java.util.Collection;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Municipio;

@Controller
@Scope("session")
public class MunicipioMBean{
	
	public Collection<Municipio> getAll( )  {
		return ArqCache.getMunicipios();
	}
}
