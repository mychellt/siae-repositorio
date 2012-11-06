package br.siae.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.DAOException;


@Service
@Transactional
public class MunicipioService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Municipio> getAll() throws DAOException {
		return cadastroService.getAll(Municipio.class);
	}
	
	public Collection<Municipio> getByEstado(Estado estado) throws DAOException {
		return cadastroService.getByExactField(Municipio.class, "estado.id", estado.getId() );
	}
}
