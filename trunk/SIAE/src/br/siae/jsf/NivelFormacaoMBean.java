package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.jsf.AbstractController;
import br.siae.dominio.academico.NivelFormacao;
import br.siae.service.NivelFormacaoService;

@Controller
@Scope("request")
public class NivelFormacaoMBean extends AbstractController implements ArqException {
	@Resource(name="nivelFormacaoService")
	private NivelFormacaoService service;
	
	public Collection<NivelFormacao> getAll( ) {
		try {
			return service.getAll(NivelFormacao.class);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<NivelFormacao>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}
}
