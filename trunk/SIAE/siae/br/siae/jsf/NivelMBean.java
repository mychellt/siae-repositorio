package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.DAOException;
import br.siae.arq.jsf.AbstractController;
import br.siae.dominio.academico.Nivel;
import br.siae.service.NivelService;

@Controller
@Scope("request")
public class NivelMBean extends AbstractController{
	
	@Resource(name="nivelService")
	private NivelService service;
	
	public Collection<Nivel> getAll( ) {
		try {
			return service.getAll(Nivel.class);
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Nivel>();
	}
			
}
