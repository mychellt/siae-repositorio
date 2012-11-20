package br.siae.jsf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.ArqException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.dominio.rh.Cargo;

@Controller
@Scope("session")
public class CargoMBean extends AbstractSiaeController<Cargo> implements ArqException {

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return e.getMessage();
	}

}
