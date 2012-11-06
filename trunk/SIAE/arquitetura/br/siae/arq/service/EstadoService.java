package br.siae.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Estado;
import br.siae.arq.erro.DAOException;

@Transactional
@Service
public class EstadoService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Estado> getAll() throws DAOException {
		return cadastroService.getAll(Estado.class);
	}
}
