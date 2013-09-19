package br.arq.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.seguranca.Permissao;
import br.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PermissaoService extends AbstractService{
	

	public Permissao executeCadastro( Permissao permissao ) throws NegocioException {
		if( ValidatorUtil.isEmpty( permissao ) ){
			permissao = (Permissao) cadastrar(permissao);
		}
		else {
			permissao = (Permissao) alterar(permissao);
		}
		return permissao;
	}
	
	
	public Permissao executeRemocao( Permissao permissao ) throws NegocioException {
		permissao = (Permissao) remover(permissao);
		return permissao;
	}
}
