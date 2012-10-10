package br.siae.arq.jsf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Usuario;

@Controller
@Scope("session")
public class UsuarioMBean extends AbstractCrudController<Usuario>{
	
	public static final String FORM_IMPLANTAR_PERMISSOES = "/views/restrito/usuario/form_implantar_permissoes";
	
	public UsuarioMBean() {
		obj = new Usuario();
	}
	
	public String implantarPermissoes() {
		return FORM_IMPLANTAR_PERMISSOES;
	}
}
