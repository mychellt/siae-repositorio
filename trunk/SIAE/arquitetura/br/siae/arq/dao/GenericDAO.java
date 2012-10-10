package br.siae.arq.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.erro.DAOException;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.DAOUtils;


@Repository
public  class GenericDAO {
	
	
	@Autowired
	private HibernateTemplate ht;
	private void change(char op, Persistable obj){		
		switch (op) {
		case 'C':
		case 'U':
			ht.saveOrUpdate(obj);
			break;
		case 'D':
			ht.delete(obj);
			break;
		}
	}
	
	public void close() {
		ht.getSessionFactory().close();
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
	
	public <T extends Persistable> T findByPrimaryKey( Class<T> classe, long id){
		return ht.get( classe, id );
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T>  findAll( Class<T> classe ){
		DetachedCriteria criteria = DetachedCriteria.forClass( classe );  
		return ht.findByCriteria( criteria );  
	}
	public <T> Collection<T> findByExactField(Class<T> classe, String field, Object value) throws DAOException {
		return findWithQuery(classe, field, value, true, false, null, new String[0]);
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

		Session openSession = ht.getSessionFactory().openSession();
		Query q = openSession.createQuery(query + orderQuery);
		return q.list();
	}
	
	public Session getSession() {
		return ht.getSessionFactory().openSession();
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) ServiceFactory.getBean("jdbcTemplate");
	}
}