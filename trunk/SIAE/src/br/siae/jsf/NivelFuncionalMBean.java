package br.siae.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.cache.ArqCache;
import br.siae.dominio.rh.NivelFuncional;

@Controller
@Scope("request")
public class NivelFuncionalMBean {
	public Collection<NivelFuncional> getAll() {
		return ArqCache.getNiveisFuncionais();
	}
}
