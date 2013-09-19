package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.jsf.AbstractController;
import br.siae.dominio.academico.Nivel;
import br.siae.service.NivelService;

@Controller
@Scope("request")
public class NivelMBean extends AbstractController implements ArqException{
	
	@Resource(name="nivelService")
	private NivelService service;
	
	public Collection<Nivel> getAll( ) {
		try {
			return service.getAll(Nivel.class);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Nivel>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
			
}
