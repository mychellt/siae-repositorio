package br.arq.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.UsuarioDAO;
import br.arq.erros.MensagemNegocio;
import br.arq.erros.NegocioException;
import br.arq.seguranca.Permissao;
import br.arq.seguranca.PermissaoUsuario;
import br.arq.seguranca.Usuario;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;

@Transactional
@Service
public class UsuarioService extends AbstractService{
	@Resource(name="usuarioDAO")
	private UsuarioDAO dao;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
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
			usuario.setSenha( DAOUtils.toMD5(usuario.getSenha(), null) );
			usuario.setAtivo(true);
			usuario.setDataCadastro( new Date() );
			cadastrar(usuario);
		}
		else {
			Collection<PermissaoUsuario> colecao = new ArrayList<PermissaoUsuario>( usuario.getPermissoes() );
			for( PermissaoUsuario pu : colecao ) {
				if( !permissoes.contains(pu.getPermissao())) {
					remover(pu);
					usuario.getPermissoes().remove(pu);
				}
			}
			for( Permissao permissao : permissoes ) {
				PermissaoUsuario pu = new PermissaoUsuario();
				pu.setPermissao( permissao );
				pu.setUsuario(usuario);
				usuario.getPermissoes().add(pu);
			}
			usuario.setSenha( DAOUtils.toMD5(usuario.getSenha(), null) );
			alterar(usuario);
		}
		return usuario;
	}

	private void validate(Usuario usuario) throws NegocioException {
		if( !usuario.getSenha().trim().equals( usuario.getSenhaConfirmacao().trim() ) ) {
			throw new NegocioException(MensagemNegocio.SENHAS_DIFERENTES);
		}
		List<Usuario> lista = (List<Usuario>) getByExactField(Usuario.class, "login", usuario.getLogin() );
		if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != usuario.getId()) {
			throw new NegocioException(MensagemNegocio.USUARIO_LOGIN_JA_CADASTRADO);
		}
	}
	
	public Usuario getByLogin( String login ) throws NegocioException {
		return dao.findByLogin(login);
	}
}
