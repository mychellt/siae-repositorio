package br.siae.negocio;

import java.util.List;

import org.springframework.stereotype.Service;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.negocio.ProcessadorCadastro;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;

@Service
public class ProcessadorSerie extends ProcessadorCadastro {
		public Serie executarCadastro(Serie serie) throws NegocioException, DAOException {
			if( ValidatorUtil.isEmpty( serie ) ){
				serie = (Serie) cadastrar(serie);
			}
			else {
				GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
				String[] fields = new String[]{"denominacao", "nivel.id"};
				Object[] values = new Object[]{ serie.getDenominacao(), serie.getNivel().getId() };
				List<Serie> lista = (List<Serie>) dao.findByExactFields( Serie.class, fields, values );
				if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() == serie.getId() ) {
					throw new NegocioException("J� existe uma s�rie cadastrada com essa denomina��o para este n�vel.");
				}
				serie = (Serie) alterar(serie);
			}
			return serie;
		}
}
