package br.siae.arq.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Logradouro;

@Component
@Scope("request")
public class LogradouroMBean{
	
	public Collection<Logradouro> getAll() {
		return ArqCache.getLogradouros();
	}
}
