package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Ano;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AnoService;
	
@Controller
@Scope("request")
public class AnoMBean extends AbstractSiaeController<Ano>{
	@Resource(name="anoService")
	private AnoService service;
	
	public List<Ano> getAll( ) {
		try {
			return (List<Ano>) service.getAll(Ano.class);
		} catch (NegocioException e) {
			e.printStackTrace();
		}
		return new ArrayList<Ano>();
	}

}
