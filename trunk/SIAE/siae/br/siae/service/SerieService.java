package br.siae.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;

@Service
@Transactional
public class SerieService extends AbstractService{
	

	public Serie executeCadastro(Serie serie) throws NegocioException, DAOException {
		if( ValidatorUtil.isEmpty( serie ) ){
			String[] fields = new String[]{"denominacao", "nivel.id"};
			Object[] values = new Object[]{ serie.getDenominacao(), serie.getNivel().getId() };
			List<Serie> lista = (List<Serie>) getGenericDAO().findByExactFields( Serie.class, fields, values );
			if( ValidatorUtil.isNotEmpty(lista) ) {
				throw new NegocioException("J� existe uma s�rie cadastrada com essa denomina��o para este n�vel.");
			}
			serie = (Serie)cadastrar(serie);
			serie.setNivel( getGenericDAO().findByPrimaryKey( Nivel.class, serie.getNivel().getId() ) );
		}
		else {
			String[] fields = new String[]{"denominacao", "nivel.id"};
			Object[] values = new Object[]{ serie.getDenominacao(), serie.getNivel().getId() };
			List<Serie> lista = (List<Serie>) getGenericDAO().findByExactFields( Serie.class, fields, values );
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() == serie.getId() ) {
				throw new NegocioException("J� existe uma s�rie cadastrada com essa denomina��o para este n�vel.");
			}
			serie = (Serie) alterar(serie);
		}
		return serie;
	}
		
	public Serie executeRemocao(Serie serie) throws NegocioException {
		try {
			return (Serie) remover(serie);
		} catch (DAOException e) {
			throw new NegocioException( e.getMessage() );
		}
	}
}
