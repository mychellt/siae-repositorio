package br.siae.arq.service;

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
		if( ValidatorUtil.isEmpty( permissao ) ){
			try {
				permissao = (Permissao) cadastrar(permissao);
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new NegocioException(e);
			}
		}
		else {
			permissao = (Permissao) alterar(permissao);
		}
		return permissao;
	}
	
	
	public Permissao executeRemocao( Permissao permissao ) throws NegocioException, DAOException {
		permissao = (Permissao) remover(permissao);
		return permissao;
	}
}
