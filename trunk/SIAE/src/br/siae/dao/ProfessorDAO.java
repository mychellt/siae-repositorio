package br.siae.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Pessoa;
import br.siae.dominio.rh.Professor;


@Repository
@Transactional
public class ProfessorDAO extends GenericDAO{

	public Collection<Professor> findByCriterios( String nome, String nomeMae, String nomePai, Long cpf, Date nascimento, Long rg, Long registroNascimento ) throws DataAccessException {
//			Criteria c = getSession().createCriteria( Professor.class );
//			c.createAlias("pessoa", "p");
//			if( ValidatorUtil.isNotEmpty(nome) ){
//				c.add( Restrictions.ilike("p.nome", nome ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nomeMae) ){
//				c.add( Restrictions.ilike("p.nomeMae", nomeMae ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nomePai) ){
//				c.add( Restrictions.ilike("p.nomePai", nomePai ) );
//			}
//			if( ValidatorUtil.isNotEmpty(cpf) ){
//				c.add( Restrictions.eq("p.cpf", cpf ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nascimento) ){
//				c.add( Restrictions.eq("p.dataNascimento", nascimento ) );
//			}
//			if( ValidatorUtil.isNotEmpty(rg) ){
//				c.createAlias("p.identidade", "rg");
//				c.add( Restrictions.eq("rg", rg ) );
//			}
//			if( ValidatorUtil.isNotEmpty(registroNascimento) ){
//				c.createAlias("p.certidaoNascimento", "cn");
//				c.add( Restrictions.eq("cn.numero", registroNascimento ) );
//			}
//			
//			@SuppressWarnings("unchecked")
//			Collection<Professor> lista = c.list();
//			return lista;
			return null;
	}
	
	public Collection<Professor> findByNome( String nome ) throws DataAccessException {
		String hql = "select prof from Professor prof join prof.pessoa p where upper(p.nome) like upper(:nome) order by p.nome asc ";
		Query q = getEntityManager().createQuery(hql);
		q.setParameter("nome", "%" + nome + "%");
		
		@SuppressWarnings("unchecked")
		List<Professor> lista = q.getResultList();
		return lista;
	}
	public Professor findByPessoa( Pessoa pessoa ) throws DataAccessException {
		String hql = "select prof from Professor prof join prof.pessoa p where p.id = :idPessoa ";
		Query q = getEntityManager().createQuery(hql);
		q.setParameter("idPessoa", pessoa.getId() );
		
		Professor professor = (Professor) q.getSingleResult();
		return professor;	
	}
}
