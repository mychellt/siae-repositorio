package br.siae.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.utils.ValidatorUtil;

@Service
public class PermissaoService extends CadastroService {
	
	public Permissao executarCadastro( Permissao permissao ) throws NegocioException, DAOException {
		if( ValidatorUtil.isEmpty( permissao ) ){
			permissao = (Permissao) cadastrar(permissao);
		}
		else {
			GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
			List<Permissao> lista = (List<Permissao>) dao.findByLikeField(Permissao.class, "denominacao", permissao.getDenominacao() );
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() == permissao.getId() ) {
				throw new NegocioException("J� existe uma permiss�o cadastrada com esse denomina��o.");
			}
			permissao = (Permissao) alterar(permissao);
		}
		return permissao;
	}
}
