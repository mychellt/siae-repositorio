package br.siae.arq.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
	    this.entityManager = entityManager;
	}
	
	private void change(char op, Persistable obj) throws DAOException {
		try {
			switch (op) {
			case 'C':
				break;
			case 'U':
				break;
			case 'D':
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e.getMessage()); 
		}
	}
	
	public void create( Persistable obj) throws DAOException{
		change( 'C', obj );
	}
	
	public void delete( Persistable obj ) throws DAOException{
		change( 'D', obj );
	}
	
	public void update( Persistable obj ) throws DAOException{
		change('U', obj );
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> findAllAtivos( Class<T> classe ) {
//		Criteria c = getSession().createCriteria(classe);
//		c.setCacheable(true);
//		c.add( Restrictions.eq("ativo", Boolean.TRUE) );
//		return c.list();
		return null;
	}
	@SuppressWarnings("unchecked")
	public <T extends Persistable> T findByPrimaryKey( Class<T> classe, long id){
//		return (T) getSession().get( classe, id );
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T>  findAll( Class<T> classe ){
//		Criteria c = getSession().createCriteria(classe);
//		c.setCacheable(true);
//		return c.list();
		return null;
	}
	public <T> Collection<T> findByExactField(Class<T> classe, String field, Object value) throws DAOException {
		return findWithQuery(classe, field, value, true, false, null, new String[0]);
	}
	
	
	
//	public void updateField(Class<?> classe, Integer id, String campo, Object valor) throws DAOException, SQLException {
//		Statement st = null;
//		Connection con = null;
//		try {
//			con = getSession().connection();
//			st = con.createStatement();
//
//			String query = null;
//			query = DAOUtils.createUpdateQuery(sessionFactory, classe, id, campo, valor);
//			
//			st.addBatch(query);
//
//			st.executeBatch();
//
//		} catch (Exception e) {
//			throw new DAOException(e);
//		} finally {
//			st.close();
//			con.close();
//		}
//	}

	
//	public void updateFields(Class<?> classe, Integer id, String[] campos, Object[] valores) throws DAOException, SQLException {
//		Statement st = null;
//		Connection con = null;
//		try {
//			String query = DAOUtils.createUpdateQuery(sessionFactory, classe, id, campos, valores);
//
//			con = getSession().connection();
//			st = con.createStatement();
//			st.executeUpdate(query);
//
//
//		} catch (Exception e) {
//			throw new DAOException(e);
//		} finally {
//			st.close();
//			con.close();
//		}
//	}
//	
	
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

		
		return null;
	}
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) ServiceFactory.getBean("jdbcTemplate");
	}
}