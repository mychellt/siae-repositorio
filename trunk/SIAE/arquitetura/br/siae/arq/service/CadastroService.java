package br.siae.arq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Persistable;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;

@Service
public class CadastroService {
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Persistable cadastrar( Persistable obj) throws DAOException {
		try {
			dao.create( obj );
		} catch (Exception e) {
			throw new DAOException(e);
		}
		finally {
			dao.close();
		}
		return obj;
	}
	public Persistable remover( Persistable obj ) throws DAOException, NegocioException {
		try {
			dao.delete( obj );
		} catch (Exception e) {
			throw new DAOException(e);
		}
		finally {
			dao.close();
		}
		return obj;
	}
	
	public Persistable alterar( Persistable obj ) throws DAOException {
		try {
			dao.update( obj );
		} catch (Exception e) {
			throw new DAOException(e);
		}
		finally {
			dao.close();
		}
		return obj;
	}
}
