package br.siae.jsf;

import java.util.Collection;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.dominio.rh.NivelFuncional;
import br.siae.utils.SIAECache;

@Controller
@Scope("request")
public class NivelFuncionalMBean {
	public Collection<NivelFuncional> getAll() {
		return SIAECache.getNiveisFuncionais();
	}
}
