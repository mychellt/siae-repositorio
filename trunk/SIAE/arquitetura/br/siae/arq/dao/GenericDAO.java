package br.siae.arq.dao;

import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.DAOUtils;


@Repository
@Transactional
public  class GenericDAO {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private void change(char op, Persistable obj){		
		switch (op) {
		case 'C':
		case 'U':
			getSession().saveOrUpdate(obj);
			break;
		case 'D':
			getSession().delete(obj);
			break;
		}
	}
	
	public void create( Persistable obj){
		change( 'C', obj );
	}
	
	public void delete( Persistable obj ){
		change( 'D', obj );
	}
	
	public void update( Persistable obj ){
		change('U', obj );
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> findAllAtivos( Class<T> classe ) {
		Criteria c = getSession().createCriteria(classe);
		c.setCacheable(true);
		c.add( Restrictions.eq("ativo", true) );
		return c.list();
	}
	@SuppressWarnings("unchecked")
	public <T extends Persistable> T findByPrimaryKey( Class<T> classe, long id){
		return (T) getSession().get( classe, id );
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T>  findAll( Class<T> classe ){
		Criteria c = getSession().createCriteria(classe);
		c.setCacheable(true);
		return c.list();  
	}
	public <T> Collection<T> findByExactField(Class<T> classe, String field, Object value) throws DAOException {
		return findWithQuery(classe, field, value, true, false, null, new String[0]);
	}
	
	public <T> Collection<T> findByExactFields(Class<T> classe, String[] fields, Object[] values) throws DAOException{
		return findWithQuery(classe, null, null, true, false, null,fields, values, new String[0]);
	}
	
	
	private <T> Collection<T> findWithQuery(Class<T> classe, String field, Object value, boolean exact, boolean init, String orderType, String... orderFields) throws DAOException {
		return findWithQuery(classe, field, value, exact, init, orderType, null, null, orderFields);
	}

	
	public <T> Collection<T> findByLikeField(Class<T> classe, String field, Object value) throws DAOException {
		return findWithQuery(classe, field, value, false, false, null, new String[0]);
	}
	

	@SuppressWarnings("unchecked")
	private <T> Collection<T> findWithQuery(Class<T> classe, String field, Object value, boolean exact, boolean init, String orderType, String[] fields, Object[] values, String... orderFields) throws DAOException {
		String query = "from " + classe.getSimpleName() + " obj ";
		String orderQuery = "";
		if (exact) {

			if(value != null){
				if (value instanceof String || value instanceof Character) {
					value = "'" + DAOUtils.trataAspasSimples(value.toString()) + "'";
				}
				query += " where obj." + field + "=" + value;
			}

			if(fields != null && fields.length > 0){
				int indice = 0;
				if(value == null){
					query += " where 1=1 ";
				}
				for(String f : fields){
					query += " and obj." + f + "=" + ("'" + DAOUtils.trataAspasSimples(values[indice].toString()) + "'");
					indice++;
				}
			}

		} else if (!init){
			query += " where to_ascii(upper(obj." + field + "), 'LATIN9') like to_ascii(upper('%" + DAOUtils.trataAspasSimples(value.toString()) + "%'),'LATIN9')";
		} else{
			query += " where to_ascii(upper(obj." + field + "), 'LATIN9') like to_ascii(upper('" + DAOUtils.trataAspasSimples(value.toString()) + "%'),'LATIN9')";
		}
		if (orderType != null & orderFields.length > 0) {
			orderQuery += " order by ";
			for (String f : orderFields) {
				orderQuery += f + ",";
			}
			orderQuery = orderQuery.substring(0, orderQuery
					.lastIndexOf(','));
			orderQuery += " " + orderType;

		}

		Query q = getSession().createQuery(query + orderQuery);
		return q.list();
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) ServiceFactory.getBean("jdbcTemplate");
	}
}