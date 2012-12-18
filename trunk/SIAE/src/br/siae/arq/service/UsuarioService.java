package br.siae.arq.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.utils.ValidatorUtil;

@Transactional
@Service
public class UsuarioService extends AbstractService{
	public Usuario executeAssociacao( Usuario usuario ) throws NegocioException {
		validate(usuario);
		if( ValidatorUtil.isEmpty(usuario) ) {
			cadastrar(usuario);
		}
		return usuario;
	}

	private void validate(Usuario usuario) throws NegocioException {
		if( !usuario.getSenha().trim().equals( usuario.getSenhaConfirmacao().trim() ) ) {
			throw new NegocioException("As senhas informadas não são iguais");
		}
	}
}
