package br.siae.arq.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.utils.DAOUtils;

@Repository
@Transactional
public class UsuarioDAO extends GenericDAO{
	public boolean autenticaUsuario(String login, String senha, boolean fazerHash) throws DataAccessException {
		String senhaBD = getJdbcTemplate().queryForObject("select senha from comum.usuario where lower(login)=lower(?)", new Object[] { login }, String.class);
		String senhaComparacao = (fazerHash ? DAOUtils.toMD5(senha, " ") : senha);
		if (senhaComparacao.equals(senhaBD)) {
			return true;
		} else {
			return false;
		}
	}
	
	public Usuario findByLogin( String login ) throws DataAccessException {
//			Query q = getSession().createQuery("select u from Usuario u where u.login = :login");
//			q.setParameter("login", login);
//			Usuario u = (Usuario) q.uniqueResult();
//			return u;
			return null;
		}
}
