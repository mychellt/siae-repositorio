package br.siae.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;

@Service
@Transactional
public class SerieService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Serie executeCadastro(Serie serie) throws NegocioException, DAOException {
		GenericDAO dao = cadastroService.getGenericDAO();
		if( ValidatorUtil.isEmpty( serie ) ){
			String[] fields = new String[]{"denominacao", "nivel.id"};
			Object[] values = new Object[]{ serie.getDenominacao(), serie.getNivel().getId() };
			List<Serie> lista = (List<Serie>) dao.findByExactFields( Serie.class, fields, values );
			if( ValidatorUtil.isNotEmpty(lista) ) {
				throw new NegocioException("Jé existe uma série cadastrada com essa denominação para este nível.");
			}
			serie = (Serie)cadastroService.cadastrar(serie);
			serie.setNivel( dao.findByPrimaryKey( Nivel.class, serie.getNivel().getId() ) );
		}
		else {
			String[] fields = new String[]{"denominacao", "nivel.id"};
			Object[] values = new Object[]{ serie.getDenominacao(), serie.getNivel().getId() };
			List<Serie> lista = (List<Serie>) dao.findByExactFields( Serie.class, fields, values );
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() == serie.getId() ) {
				throw new NegocioException("Jé existe uma série cadastrada com essa denominação para este nível.");
			}
			serie = (Serie) cadastroService.alterar(serie);
		}
		return serie;
	}
		
	public Serie executeRemocao(Serie serie) throws NegocioException {
		try {
			return (Serie) cadastroService.remover(serie);
		} catch (DAOException e) {
			throw new NegocioException( e.getMessage() );
		}
	}
	
	public Collection<Serie> getAll( ) throws DAOException{
		return cadastroService.getAll( Serie.class ); 
	}
}
