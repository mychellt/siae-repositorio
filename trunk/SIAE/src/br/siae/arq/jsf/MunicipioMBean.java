package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.MunicipioService;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class MunicipioMBean extends AbstractSiaeController<Municipio> implements ArqException{
	
	@Resource(name="municipioService")
	private MunicipioService service;
	
	private Estado estado;
	
	public MunicipioMBean() {
		estado = new Estado();
		lista = new ArrayList<Municipio>();
	}
	
	public Collection<Municipio> getAll( )  {
		try {
			if( ValidatorUtil.isEmpty(lista)){
				lista = (List<Municipio>) service.getAll(Municipio.class);
			}
			return lista;
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
		}
		return new ArrayList<Municipio>();
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void changeEstado(){
		lista = new ArrayList<Municipio>();
		if( ValidatorUtil.isNotEmpty( estado ) ) {
			try {
				lista = (List<Municipio>) service.getByEstado( estado );
			} catch (Exception e) {
				addMensagemErro( processaException(e) );
			}
		}
	}
	
	public Collection<Municipio> getMunicipios() {
		return lista;
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		return "Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.";
	}
}
