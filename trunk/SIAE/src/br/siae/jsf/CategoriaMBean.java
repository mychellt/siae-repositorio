package br.siae.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.utils.ArqCache;
import br.siae.dominio.rh.Categoria;


@Controller
@Scope("request")
public class CategoriaMBean {
	public Collection<Categoria> getAll() {
		return ArqCache.getCategorias();
	}
}
