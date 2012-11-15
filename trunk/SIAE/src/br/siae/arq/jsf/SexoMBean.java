package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Sexo;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.SexoService;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class SexoMBean extends AbstractSiaeController<Sexo> implements ArqException{
	@Resource(name="sexoService")
	private SexoService service;
	
	public Collection<Sexo> getAll( ) {
		try {
			if( ValidatorUtil.isEmpty(lista)){
				lista = (List<Sexo>) service.getAll(Sexo.class);
			}
			return lista;
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Sexo>();
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return "Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.";
	}
}
