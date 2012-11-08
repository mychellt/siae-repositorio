package br.siae.arq.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PermissaoService extends AbstractService{
	

	public Permissao executeCadastro( Permissao permissao ) throws NegocioException, DAOException {
		List<Permissao> lista = (List<Permissao>) getGenericDAO().findByLikeField(Permissao.class, "denominacao", permissao.getDenominacao() );
		if( ValidatorUtil.isEmpty( permissao ) ){
			if( ValidatorUtil.isNotEmpty(lista) ){
				throw new NegocioException("Já existe uma permissão cadastrada com essa denominação.");
			}
			permissao = (Permissao) cadastrar(permissao);
		}
		else {
			
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != permissao.getId() ) {
				throw new NegocioException("Já existe uma permissão cadastrada com essa denominação.");
			}
			permissao = (Permissao) alterar(permissao);
		}
		return permissao;
	}
	
	
	public Permissao executeRemocao( Permissao permissao ) throws NegocioException, DAOException {
		permissao = (Permissao) remover(permissao);
		return permissao;
	}
}
