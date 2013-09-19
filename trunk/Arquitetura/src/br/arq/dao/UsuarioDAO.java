package br.arq.dao;

import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.arq.seguranca.Usuario;
import br.arq.utils.DAOUtils;

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
			String hql = "select u from Usuario u where lower(u.login)=lower(:login) ";
			Query q = getEntityManager().createQuery(hql);
			q.setParameter("login", login );
			
			Usuario usuario = (Usuario) q.getSingleResult();
			return usuario;
		}
}
