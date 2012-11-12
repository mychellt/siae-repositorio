package br.siae.arq.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.erro.DAOException;
import br.siae.arq.utils.DAOUtils;

@Repository
public class UsuarioDAO extends GenericDAO{
	public boolean autenticaUsuario(String login, String senha, boolean fazerHash) throws DAOException {
		String senhaBD = null;
		
		try {
			senhaBD = getJdbcTemplate().queryForObject("select senha from comum.usuario where lower(login)=lower(?)", new Object[] { login }, String.class);
		} catch(EmptyResultDataAccessException e) {
			return false;
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
		
		String senhaComparacao = (fazerHash ? DAOUtils.toMD5(senha, " ") : senha);
		if (senhaComparacao.equals(senhaBD)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Usuario findByLogin( String login ) throws DAOException {
		try {
//			Query q = getSession().createQuery("select u from Usuario u where u.login = :login");
//			q.setParameter("login", login);
//			Usuario u = (Usuario) q.uniqueResult();
//			return u;
			return null;
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

}
