package br.siae.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;

@Repository
@Transactional
public class AlunoDAO extends GenericDAO{
	
	public List<Aluno> findByCriterios( String nome, String nomeMae, String nomePai, Long cpf, Date dataNascimento, Long rg, Long registroNascimento ) throws DataAccessException {
			EntityManager em = getEntityManager();
			StringBuilder hql = new StringBuilder();
			hql.append("select a from Aluno a join aluno.pessoa p ")
			   .append("left join p.certidaoNascimento cn ")
			   .append("left join p.identidade rg where 1=1 ");
			if(ValidatorUtil.isNotEmpty(nome)){
				hql.append("and p.nome like :nome ");
			}
			if(ValidatorUtil.isNotEmpty(nomePai)){
				hql.append("and p.nomePai like :nomePai ");
			}
			if(ValidatorUtil.isNotEmpty(nomeMae)){
				hql.append("and p.nomeMae like :nomeMae ");
			}
			if(ValidatorUtil.isNotEmpty(cpf)){
				hql.append("and p.cpf = :cpf ");
			}
			if(ValidatorUtil.isNotEmpty(dataNascimento)){
				hql.append("and p.dataNascimento = :dataNascimento ");
			}
			if(ValidatorUtil.isNotEmpty(rg)){
				hql.append("and rg.numero = :rg ");
			}
			if(ValidatorUtil.isNotEmpty(registroNascimento)){
				hql.append("and cn.numero = :registroNascimento ");
			}
			Query query = em.createQuery(hql.toString());
			
			if(ValidatorUtil.isNotEmpty(nome)){
				query.setParameter("nome", nome);
			}
			if(ValidatorUtil.isNotEmpty(nomePai)){
				query.setParameter("nomePai", nomePai);
			}
			if(ValidatorUtil.isNotEmpty(nomeMae)){
				query.setParameter("nomeMae", nomeMae);
			}
			if(ValidatorUtil.isNotEmpty(cpf)){
				query.setParameter("cpf", cpf);
			}
			if(ValidatorUtil.isNotEmpty(dataNascimento)){
				query.setParameter("dataNascimento", dataNascimento);
			}
			if(ValidatorUtil.isNotEmpty(rg)){
				query.setParameter("rg", rg);
			}
			if(ValidatorUtil.isNotEmpty(registroNascimento)){
				query.setParameter("registroNascimento", registroNascimento );
			}
			
			@SuppressWarnings("unchecked")
			List<Aluno> colecao = query.getResultList();
			return colecao;
			
	}
	public Aluno findByPessoa( Pessoa pessoa ) throws DataAccessException {
		String hql = "select a from Aluno a join a.pessoa p where p.id = :idPessoa ";
		Query q = getEntityManager().createQuery(hql);
		q.setParameter("idPessoa", pessoa.getId() );
		
		Aluno aluno = (Aluno) q.getSingleResult();
		return aluno;
	}
}

