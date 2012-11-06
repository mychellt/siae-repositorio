package br.siae.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Pais;
import br.siae.arq.erro.DAOException;

@Service
@Transactional
public class PaisService {

	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Pais> getAll() throws DAOException {
		return cadastroService.getAll(Pais.class);
	}
}
