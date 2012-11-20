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
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.NivelFormacao;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.comum.Instituicao;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Categoria;
import br.siae.dominio.rh.NivelFuncional;

@Service
@Transactional
public class ArqService extends AbstractService{
	public void executeCaching() throws NegocioException {
		ArqCache.setSexos( getAll( Sexo.class ) );
		ArqCache.setEstados( getAll( Estado.class ) );
		ArqCache.setMunicipios( getAll(Municipio.class) );
		ArqCache.setPaises( getAll(Pais.class) );
		ArqCache.setLogradouros( getAll(Logradouro.class) );
		ArqCache.setNiveis( getAll(Nivel.class) );
		ArqCache.setTurnos( getAll(Turno.class) );
		ArqCache.setSeries( getAll(Serie.class) );
		ArqCache.setNiveisFormacao( getAll(NivelFormacao.class) );
		ArqCache.setInstituicoes( getAll(Instituicao.class) );
		ArqCache.setNiveisFuncionais( getAll(NivelFuncional.class) );
		ArqCache.setCategorias( getAll(Categoria.class) );
	}
}
