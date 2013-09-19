package br.arq.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dominio.Estado;
import br.arq.dominio.Municipio;
import br.arq.erros.NegocioException;


@Service
@Transactional
public class MunicipioService extends AbstractService{

	public Collection<Municipio> getByEstado(Estado estado) throws NegocioException {
		return getByExactField(Municipio.class, "estado.id", estado.getId() );
	}
}
