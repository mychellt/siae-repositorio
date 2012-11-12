package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.MunicipioService;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("request")
public class MunicipioMBean extends AbstractController{
	
	@Resource(name="municipioService")
	private MunicipioService service;
	
	private Estado estado;
	
	private Collection<Municipio> municipios;
	
	public MunicipioMBean() {
		estado = new Estado();
		setMunicipios(new ArrayList<Municipio>());
	}
	
	public Collection<Municipio> getAll( ) throws DAOException {
		return municipios;
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void changeEstado(){
		municipios = new ArrayList<Municipio>();
		if( ValidatorUtil.isNotEmpty( estado ) ) {
			try {
				municipios = service.getByEstado( estado );
			} catch (DAOException e) {
				addMensagemErro("Ocorreu um erro ao tentar recuperar os registros. Por favor, entre em contato com o administrador do sistema.");
				e.printStackTrace();
			}
		}
	}
	public Collection<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(Collection<Municipio> municipios) {
		this.municipios = municipios;
	}
}
