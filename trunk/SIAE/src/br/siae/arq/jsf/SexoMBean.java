package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Sexo;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.SexoService;

@Controller
@Scope("request")
public class SexoMBean extends AbstractController{
	@Resource(name="sexoService")
	private SexoService service;
	
	public Collection<Sexo> getAll( ) {
		try {
			return service.getAll(Sexo.class);
		} catch (DAOException e) {
			addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
			e.printStackTrace();
		}
		return new ArrayList<Sexo>();
	}
}
