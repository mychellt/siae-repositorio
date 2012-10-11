package br.siae.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.DAOException;

@Controller
@Scope("request")
public class MunicipioMBean {
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	
	public Collection<Municipio> getAllByEstado( Estado estado ) throws DAOException {
		return dao.findByExactField( Municipio.class, "estado.id", estado.getId() );
	}
	public Collection<Municipio> complete( String nome ) throws DAOException {
			return dao.findByLikeField(Municipio.class, "nome", nome );
	}
}
