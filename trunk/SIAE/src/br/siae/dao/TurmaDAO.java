package br.siae.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.GenericDAO;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.comum.Turno;

@Repository
@Transactional
public class TurmaDAO extends GenericDAO{
	public List<Turma> findByCriterios( String nome, Serie serie, Turno turno, Integer ano ) throws DataAccessException {
		EntityManager em = getEntityManager();
		StringBuffer hql = new StringBuffer();
		hql.append("select turma from Turma turma join turma.turno turno join turma.serie serie where 1=1 ");
		if(ValidatorUtil.isNotEmpty(nome) ) {
			hql.append(" and t.nome ilike :nome ");
		}
		if(ValidatorUtil.isNotEmpty(serie) ) {
			hql.append(" and serie.id = :idSerie ");
		}
		if(ValidatorUtil.isNotEmpty(turno) ) {
			hql.append(" and turno.id = idTurno ");
		}
		if(ValidatorUtil.isNotEmpty(ano) ) {
			hql.append(" and turno.ano = :ano ");
		}
		
		Query query = em.createQuery(hql.toString());
		
		
		if(ValidatorUtil.isNotEmpty(nome) ) {
			query.setParameter("nome", "%" + nome + "%");
		}
		if(ValidatorUtil.isNotEmpty(serie) ) {
			query.setParameter("idSerie", serie.getId());
		}
		if(ValidatorUtil.isNotEmpty(turno) ) {
			query.setParameter("idTurno", turno.getId());
		}
		if(ValidatorUtil.isNotEmpty(ano) ) {
			query.setParameter("ano", ano);
		}
		
		@SuppressWarnings("unchecked")
		List<Turma> colecao = query.getResultList();
		return colecao;
	}
}
