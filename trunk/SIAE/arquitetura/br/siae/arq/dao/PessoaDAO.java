package br.siae.arq.dao;

import java.util.Collection;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.siae.arq.dominio.EspeciePessoa;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.utils.ValidatorUtil;

@Repository
public class PessoaDAO extends GenericDAO{
	
	public Collection<Pessoa>findByCriterios(EspeciePessoa especie, String nome, String nomeMae, String nomePai, Long cpf, Date nascimento, Long rg, Long registroNascimento ) throws DAOException {
		try {
			Criteria c = getSession().createCriteria( Pessoa.class );
			c.add( Restrictions.eq("especie", especie.getId()));
			if( ValidatorUtil.isNotEmpty(nome) ){
				c.add( Restrictions.ilike("nome", nome ) );
			}
			if( ValidatorUtil.isNotEmpty(nomeMae) ){
				c.add( Restrictions.ilike("nomeMae", nomeMae ) );
			}
			if( ValidatorUtil.isNotEmpty(nomePai) ){
				c.add( Restrictions.ilike("nomePai", nomePai ) );
			}
			if( ValidatorUtil.isNotEmpty(cpf) ){
				c.add( Restrictions.eq("cpf", cpf ) );
			}
			if( ValidatorUtil.isNotEmpty(nascimento) ){
				c.add( Restrictions.eq("dataNascimento", nascimento ) );
			}
			if( ValidatorUtil.isNotEmpty(rg) ){
				c.add( Restrictions.eq("rg", rg ) );
			}
			if( ValidatorUtil.isNotEmpty(registroNascimento) ){
				c.createAlias("certidaoNascimento", "cn");
				c.add( Restrictions.eq("cn.numero", registroNascimento ) );
			}
			
			@SuppressWarnings("unchecked")
			Collection<Pessoa> lista = c.list();
			return lista;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
