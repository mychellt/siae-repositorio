package br.siae.arq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PermissaoService{
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Permissao executeCadastro( Permissao permissao ) throws NegocioException, DAOException {
		if( ValidatorUtil.isEmpty( permissao ) ){
			permissao = (Permissao) cadastroService.cadastrar(permissao);
		}
		else {
			GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
			List<Permissao> lista = (List<Permissao>) dao.findByLikeField(Permissao.class, "denominacao", permissao.getDenominacao() );
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() == permissao.getId() ) {
				throw new NegocioException("Já existe uma permissão cadastrada com essa denominação.");
			}
			permissao = (Permissao) cadastroService.alterar(permissao);
		}
		return permissao;
	}
	
	
	public Permissao executeRemocao( Permissao permissao ) throws NegocioException, DAOException {
		permissao = (Permissao) cadastroService.remover(permissao);
		return permissao;
	}
}
