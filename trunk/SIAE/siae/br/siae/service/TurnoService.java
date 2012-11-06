package br.siae.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.service.CadastroService;
import br.siae.dominio.comum.Turno;


@Transactional
@Service
public class TurnoService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Collection<Turno> getAll() throws DAOException {
		return cadastroService.getAll( Turno.class );
	}
}
