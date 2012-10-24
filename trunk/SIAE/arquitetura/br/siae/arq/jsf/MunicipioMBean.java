package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.DAOException;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class MunicipioMBean {
	
	private Estado estado;
	private Collection<Municipio> municipios;
	
	public MunicipioMBean() {
		estado = new Estado();
		setMunicipios(new ArrayList<Municipio>());
	}
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	
	public Collection<Municipio> getAll( ) throws DAOException {
		return municipios;
	}
	public Collection<Municipio> complete( String nome ) throws DAOException {
			return dao.findByLikeField(Municipio.class, "nome", nome );
	}
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public void changeEstado() throws DAOException {
		municipios = new ArrayList<Municipio>();
		if( ValidatorUtil.isNotEmpty( estado ) ) {
			municipios = dao.findByExactField( Municipio.class, "estado.id", estado.getId() );
		}
	}
	public Collection<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(Collection<Municipio> municipios) {
		this.municipios = municipios;
	}
}
