package br.siae.dao;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.GenericDAO;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.RequerimentoMatricula;

@Repository
@Transactional
public class RequerimentoMatriculaDAO extends GenericDAO{
	public RequerimentoMatricula findByAluno(Aluno aluno) throws DataAccessException {
		String hql = "select r from RequerimentoMatricula r " +
				     " join r.aluno a " +
				     " join r.responsavelCadastro responsavel " +
				     " join r.itens item " +
				     " where a.id = :idAluno";
		Query query = getEntityManager().createQuery(hql);
		query.setParameter("idAluno", aluno.getId());
		RequerimentoMatricula requerimento = (RequerimentoMatricula) query.getSingleResult();
		return requerimento;
	}
}
