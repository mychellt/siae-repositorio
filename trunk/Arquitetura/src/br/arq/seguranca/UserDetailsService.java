package br.arq.seguranca;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import br.arq.erros.NegocioException;
import br.arq.service.UsuarioService;

public class UserDetailsService extends JdbcDaoImpl{
	
	@Resource(name="usuarioService")
	private UsuarioService service;
	
	@Override
	protected UserDetails createUserDetails(String username, UserDetails userDetails, List<GrantedAuthority> authorities) {
		Usuario usuario = null;
		try {
			usuario = service.getByLogin(userDetails.getUsername());
		} catch (NegocioException e) {
			e.printStackTrace();
			return null;
		}
		usuario.setAuthorities(authorities);		
		return usuario;
	}
}
