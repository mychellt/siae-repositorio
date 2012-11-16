package br.siae.arq.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Sexo;

@Controller
@Scope("request")
public class SexoMBean {
	
	public Collection<Sexo> getAll( ) {
		return ArqCache.getSexos();
	}
}
