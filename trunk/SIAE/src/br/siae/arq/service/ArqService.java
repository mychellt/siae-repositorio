package br.siae.arq.service;

import java.util.List;

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
		ArqCache.setSexos( (List<Sexo>) getAll( Sexo.class ) );
		ArqCache.setEstados( (List<Estado>) getAll( Estado.class ) );
		ArqCache.setMunicipios( (List<Municipio>) getAll(Municipio.class) );
		ArqCache.setPaises( (List<Pais>) getAll(Pais.class) );
		ArqCache.setLogradouros( (List<Logradouro>) getAll(Logradouro.class) );
		ArqCache.setNiveis( (List<Nivel>) getAll(Nivel.class) );
		ArqCache.setTurnos( (List<Turno>) getAll(Turno.class) );
		ArqCache.setSeries( (List<Serie>) getAll(Serie.class) );
		ArqCache.setNiveisFormacao( (List<NivelFormacao>) getAll(NivelFormacao.class) );
		ArqCache.setInstituicoes( (List<Instituicao>) getAll(Instituicao.class) );
		ArqCache.setNiveisFuncionais( (List<NivelFuncional>) getAll(NivelFuncional.class) );
		ArqCache.setCategorias( (List<Categoria>) getAll(Categoria.class) );
	}
}
