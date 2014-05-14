package br.siae.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.dominio.rh.Categoria;
import br.siae.utils.SIAECache;


@Controller
@Scope("request")
public class CategoriaMBean {
	public Collection<Categoria> getAll() {
		return SIAECache.getCategorias();
	}
}
