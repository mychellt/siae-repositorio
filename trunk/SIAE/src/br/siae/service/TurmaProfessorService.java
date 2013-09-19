package br.siae.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.siae.dao.TurmaProfessorDAO;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;

@Service
@Transactional
public class TurmaProfessorService  extends AbstractService{
	@Resource(name="turmaProfessorDAO")
	private TurmaProfessorDAO dao;
	
	public Collection<TurmaProfessor> getByTurma( Turma turma ) throws NegocioException, DataAccessException {
		return dao.findByTurma(turma);
	}
}
