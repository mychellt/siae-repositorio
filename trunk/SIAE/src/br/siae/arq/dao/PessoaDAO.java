package br.siae.arq.dao;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Query;

import org.hibernate.id.IdentityGenerator.GetGeneratedKeysDelegate;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Pessoa;

@Repository
@Transactional
public class PessoaDAO extends GenericDAO{
	
	public Collection<Pessoa>findByCriterios( String nome, String nomeMae, String nomePai, Long cpf, Date nascimento, Long rg, Long registroNascimento ) throws DataAccessException {
//			Criteria c = getSession().createCriteria( Pessoa.class );
//			if( ValidatorUtil.isNotEmpty(nome) ){
//				c.add( Restrictions.ilike("nome", nome ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nomeMae) ){
//				c.add( Restrictions.ilike("nomeMae", nomeMae ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nomePai) ){
//				c.add( Restrictions.ilike("nomePai", nomePai ) );
//			}
//			if( ValidatorUtil.isNotEmpty(cpf) ){
//				c.add( Restrictions.eq("cpf", cpf ) );
//			}
//			if( ValidatorUtil.isNotEmpty(nascimento) ){
//				c.add( Restrictions.eq("dataNascimento", nascimento ) );
//			}
//			if( ValidatorUtil.isNotEmpty(rg) ){
//				c.add( Restrictions.eq("rg", rg ) );
//			}
//			if( ValidatorUtil.isNotEmpty(registroNascimento) ){
//				c.createAlias("certidaoNascimento", "cn");
//				c.add( Restrictions.eq("cn.numero", registroNascimento ) );
//			}
//			
//			@SuppressWarnings("unchecked")
//			Collection<Pessoa> lista = c.list();
//			return lista;
			return null;
	}
	public Pessoa findByCpf( long cpf ) throws DataAccessException {
		Query q = getEntityManager().createQuery("select p From Pessoa p where p.cpf =:cpf");
		q.setParameter("cpf", cpf);
		Pessoa pessoa = (Pessoa) q.getSingleResult();
		return pessoa;
	}
}
