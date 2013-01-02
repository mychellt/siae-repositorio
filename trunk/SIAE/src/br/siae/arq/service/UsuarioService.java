package br.siae.arq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.seguranca.PermissaoUsuario;
import br.siae.arq.utils.ValidatorUtil;

@Transactional
@Service
public class UsuarioService extends AbstractService{
	public Usuario executeAssociacao( Usuario usuario,  List<Permissao> permissoes ) throws NegocioException {
		validate(usuario);
		if( ValidatorUtil.isEmpty(usuario ) ) {
			usuario.setPermissoes( new ArrayList<PermissaoUsuario>() );
			for( Permissao permissao : permissoes ) {
				PermissaoUsuario pu = new PermissaoUsuario();
				pu.setPermissao( permissao );
				pu.setUsuario(usuario);
				usuario.getPermissoes().add(pu);
			}
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
