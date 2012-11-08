package br.siae.arq.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.DAOException;


@Service
@Transactional
public class MunicipioService extends AbstractService{

	public Collection<Municipio> getByEstado(Estado estado) throws DAOException {
		return getByExactField(Municipio.class, "estado.id", estado.getId() );
	}
}
