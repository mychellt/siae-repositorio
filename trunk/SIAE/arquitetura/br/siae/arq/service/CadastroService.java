package br.siae.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Persistable;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.utils.DAOUtils;

@Service
@Transactional
public class CadastroService {
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Persistable cadastrar( Persistable obj) throws DAOException {
		try {
			dao.create( obj );
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return obj;
	}
	public Persistable remover( Persistable obj ) throws NegocioException, DAOException {
		try {
			dao.delete(obj);
		} catch (Exception e) {
			if (DAOUtils.isFKConstraintError(e)) {
				throw new NegocioException("Esse registro não pode ser removido, pois está associado a outros registros da base de dados.");
			}
			throw new DAOException(e);
		} 
		return obj;
	}
	
	public Persistable alterar( Persistable obj ) throws DAOException {
		try {
			dao.update( obj );
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return obj;
	}
	
	public GenericDAO getGenericDAO() {
		return dao;
	}
	
	public <T> Collection<T>  getAll( Class<T> classe ) throws DAOException {
		return getGenericDAO().findAll(classe);
	}
	
	public <T> Collection<T> getByExactField(Class<T> classe, String field, Object value) throws DAOException {
		return getByExactField(classe, field, value);
	}
}
