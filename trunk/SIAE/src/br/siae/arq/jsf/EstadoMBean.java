package br.siae.arq.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Estado;

@Controller
@Scope("request")
public class EstadoMBean {

	public Collection<Estado> getAll() {
		return ArqCache.getEstados();
	}
}
