package br.siae.arq.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.cache.ArqCache;
import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.dominio.Pais;
import br.siae.arq.dominio.Sexo;
import br.siae.arq.erro.NegocioException;

@Service
@Transactional
public class ArqService extends AbstractService{
	public void executeCaching() throws NegocioException {
		ArqCache.setSexos( getAll( Sexo.class ) );
		ArqCache.setEstados( getAll( Estado.class ) );
		ArqCache.setMunicipios( getAll(Municipio.class) );
		ArqCache.setPaises( getAll(Pais.class) );
		ArqCache.setLogradouros( getAll(Logradouro.class) );
	}
}
