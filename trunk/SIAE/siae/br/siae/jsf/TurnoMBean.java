package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.DAOException;
import br.siae.arq.jsf.AbstractController;
import br.siae.dominio.comum.Turno;
import br.siae.service.TurnoService;

@Controller
@Scope("request")
public class TurnoMBean extends AbstractController{
	@Resource(name="turnoService")
	private TurnoService service;
	
	public Collection<Turno> getAll( ) {
		try {
			return service.getAll();
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Turno>();
	}
		
}
