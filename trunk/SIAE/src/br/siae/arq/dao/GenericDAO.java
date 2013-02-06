package br.siae.arq.dao;

import java.util.Collection;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Persistable;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.DAOUtils;


@Repository
@Transactional
public class GenericDAO {
	
	@PersistenceContext(unitName="jpaUnit")
	private EntityManager em;
	
	public EntityManager getEntityManager() {
		return em;
	}
	
	private void change(char op, Persistable obj) throws DataAccessException{		
		switch (op) {
		case 'C':
			getEntityManager().persist(obj);
			break;
		case 'U':
			getEntityManager().merge(obj);
			break;
		case 'D':
			Persistable t = getEntityManager().merge(obj);
			getEntityManager().remove(t);
			break;
		}		
	}

	public void create( Persistable obj) throws DataAccessException{
		change( 'C', obj );
	}
	
	public void delete( Persistable obj ) throws DataAccessException{
		change( 'D', obj );
	}
	
	public void update( Persistable obj ) throws DataAccessException{
		change('U', obj );
	}
	
	public <T extends Persistable> T findByPrimaryKey( Class<T> classe, long id){
		return getEntityManager().find(classe, id );
	}
	
	public <T> Collection<T>  findAll( Class<T> classe ) throws DataAccessException{
		@SuppressWarnings("unchecked")
		List<T> lista = getEntityManager().createQuery("select c from " + classe.getSimpleName() + " c " ).getResultList();
		return lista;  
	}
	public <T> Collection<T> findByExactField(Class<T> classe, String field, Object value) throws DataAccessException {
		return findWithQuery(classe, field, value, true, false, null, new String[0]);
	}
	private <T> Collection<T> findWithQuery(Class<T> classe, String field, Object value, boolean exact, boolean init, String orderType, String... orderFields) throws DataAccessException {
		return findWithQuery(classe, field, value, exact, init, orderType, null, null, orderFields);
	}

	
	public <T> Collection<T> findByLikeField(Class<T> classe, String field, Object value) throws DataAccessException {
		return findWithQuery(classe, field, value, false, false, null, new String[0]);
	}
	

	private <T> Collection<T> findWithQuery(Class<T> classe, String field, Object value, boolean exact, boolean init, String orderType, String[] fields, Object[] values, String... orderFields) throws DataAccessException {
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

		@SuppressWarnings("unchecked")
		List<T> lista = getEntityManager().createQuery(query + orderQuery).getResultList();
		return lista;
	}
	
	
	public <T> void updateField(Class<T> classe, long id, String campo, Object valor) throws DataAccessException{
		Query q = getEntityManager().createQuery("UPDATE "+classe.getSimpleName()+" SET "+ campo +" = ? WHERE id = ?");
		int idx = 1;
		q.setParameter(idx++, valor);
		q.setParameter(idx++, id);
		q.executeUpdate();
	}

	public JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) ServiceFactory.getBean("jdbcTemplate");
	}
}