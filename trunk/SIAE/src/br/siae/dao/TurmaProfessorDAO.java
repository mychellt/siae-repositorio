package br.siae.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;

@Repository
@Transactional
public class TurmaProfessorDAO extends GenericDAO{
	public Collection<TurmaProfessor> findByTurma(  Turma turma ) throws DataAccessException {
		String hql = "select distinct tp from TurmaProfessor tp inner join tp.disciplinas disciplina join tp.turma turma where turma.id = :idTurma ";
		Query q = getEntityManager().createQuery(hql);
		q.setParameter("idTurma", turma.getId() );
		
		@SuppressWarnings("unchecked")
		List<TurmaProfessor> lista = q.getResultList();
		return lista;
	}
}
