package br.siae.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.service.CadastroService;
import br.siae.dominio.academico.Nivel;


@Service
@Transactional
public class NivelService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Nivel> getAll() throws DAOException {
		return cadastroService.getAll( Nivel.class );
	}
}
