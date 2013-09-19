package br.siae.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.GenericDAO;
import br.arq.utils.CalendarUtils;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.dominio.academico.Turma;

@Repository
@Transactional
public class MatriculaDAO extends GenericDAO{
	public long nextNumeroMatricula() {
		String esquema = "academico";
		String sequencia = "seq_" + CalendarUtils.getAnoAtual() + "_matricula";
		if(!isSequenciaExistente(esquema, sequencia)){
			super.criarSequencia(esquema, sequencia );
		}
		return getJdbcTemplate().queryForInt("select nextval('" + esquema + "." + sequencia + "');");
	}
	public RequerimentoMatricula findByAlunoTurma(Aluno aluno, Turma turma ) throws DataAccessException {
		StringBuffer hql =  new StringBuffer();
		EntityManager em = getEntityManager();
		hql.append("select rm from RequerimentoMatricula rm ")
		   .append(" join rm.aluno a join rm.itens item join item.turma turma ")
		   .append(" where a.id = :idAluno and turma.id = :idTurma ");
		Query query = em.createQuery(hql.toString());
		query.setParameter("idAluno", aluno.getId());
		query.setParameter("idTurma", turma.getId());
		return (RequerimentoMatricula) query.getSingleResult();
	}

}
