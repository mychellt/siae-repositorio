package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.comum.Turno;
import br.siae.service.TurnoService;

@Controller
@Scope("session")
public class TurnoMBean extends AbstractSiaeController<Turno> implements ArqException{
	@Resource(name="turnoService")
	private TurnoService service;
	
	public Collection<Turno> getAll( ) {
		try {
			if( ValidatorUtil.isEmpty(lista) ) {
				lista = (List<Turno>) service.getAll(Turno.class); 
			}
			return lista;
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Turno>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return "Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.";
	}
		
}
