package br.siae.arq.seguranca;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import br.siae.arq.dominio.Usuario;

public class UserDetailsService extends JdbcDaoImpl{

	@Override
	protected UserDetails createUserDetails(String username, UserDetails userDetails, List<GrantedAuthority> authorities) {
		Usuario usuario = new Usuario();
		usuario.setLogin(userDetails.getUsername());
		usuario.setSenha(userDetails.getPassword());
		usuario.setAtivo(userDetails.isEnabled());
		usuario.setAuthorities(authorities);		
		return usuario;
	}
}
