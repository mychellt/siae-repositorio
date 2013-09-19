package br.arq.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.GenericDAO;
import br.arq.dao.Persistent;
import br.arq.erros.NegocioException;

@Service
@Transactional
public abstract class AbstractService {
	
	@Resource(name="genericDAO")
	private GenericDAO dao;
	
	public Persistent cadastrar( Persistent obj) throws NegocioException {
		dao.create( obj );
		return obj;
	}
	public Persistent remover( Persistent obj ) throws NegocioException {
		dao.delete(obj);
		return obj;
	}
	
	public Persistent alterar( Persistent obj ) throws NegocioException {
		dao.update( obj );
		return obj;
	}
	
	public GenericDAO getGenericDAO() {
		return dao;
	}
	
	public <T> Collection<T>  getAll( Class<T> classe ) throws NegocioException {
		return getGenericDAO().findAll(classe);
	}
	
	public <T> Collection<T> getByExactField(Class<T> classe, String field, Object value) throws NegocioException {
		return dao.findByExactField(classe, field, value);
	}
	
	public <T extends Persistent> T getByPrimaryKey( Class<T> classe, long id){
		return getGenericDAO().findByPrimaryKey(classe, id);
	}
}
