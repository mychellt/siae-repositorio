package br.siae.negocio;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;

@Service
public class SerieService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	

	GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		
	public Serie executarCadastro(Serie serie) throws NegocioException, DAOException {
		if( ValidatorUtil.isEmpty( serie ) ){
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
		
	public Serie executarRemocao(Serie obj) throws NegocioException {
		try {
			return (Serie) cadastroService.remover(obj);
		} catch (DAOException e) {
			throw new NegocioException( e.getMessage() );
		}
	}
}
