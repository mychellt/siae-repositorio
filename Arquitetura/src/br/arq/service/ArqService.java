package br.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dominio.Estado;
import br.arq.dominio.Logradouro;
import br.arq.dominio.Municipio;
import br.arq.dominio.Pais;
import br.arq.dominio.Sexo;
import br.arq.erros.NegocioException;
import br.arq.utils.ArqCache;

@Service
@Transactional
public class ArqService extends AbstractService{
	public void executeCaching() throws NegocioException {
		ArqCache.setSexos( (List<Sexo>) getAll( Sexo.class ) );
		ArqCache.setEstados( (List<Estado>) getAll( Estado.class ) );
		ArqCache.setMunicipios( (List<Municipio>) getAll(Municipio.class) );
		ArqCache.setPaises( (List<Pais>) getAll(Pais.class) );
		ArqCache.setLogradouros( (List<Logradouro>) getAll(Logradouro.class) );
	}
}
