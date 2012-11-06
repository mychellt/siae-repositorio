package br.siae.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Logradouro;
import br.siae.arq.erro.DAOException;


@Service
@Transactional
public class LogradouroService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Logradouro> getAll() throws DAOException {
		return cadastroService.getAll(Logradouro.class);
	}
}
