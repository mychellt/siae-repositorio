package br.siae.arq.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Pais;

@Controller
@Scope("request")
public class PaisMBean {

	public Collection<Pais> getAll() {
		return ArqCache.getPaises();
	}
}
