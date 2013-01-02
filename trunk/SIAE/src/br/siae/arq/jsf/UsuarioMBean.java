package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.seguranca.PermissaoUsuario;
import br.siae.arq.service.UsuarioService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;

@Controller
@Scope("session")
public class UsuarioMBean extends AbstractSiaeController<Usuario>  implements ArqException{
	/** xhtml para associa��o de usu�rio a pessoa cadastrada.*/
	public static final String FORM_ASSOCIAR_USUARIO = "/views/restrito/usuario/associar_usuario.jsf";
	
	@Resource(name="usuarioService")
	private UsuarioService service;
	
	private Permissao permissao;
	
	public UsuarioMBean() {
		obj = new Usuario();
	}
	
	public String iniciarAssociacaoUsuario() {
		Collection<Usuario> usuarios;
		try {
			usuarios = service.getByExactField(Usuario.class, "pessoa.id", obj.getId() );
			if( ValidatorUtil.isEmpty( usuarios ) ) {
				obj.setPermissoes( new ArrayList<PermissaoUsuario>() );
				
			}
			else {
				obj = ((List<Usuario>) usuarios).get(0);			
			}
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
			return null;
		}
		return FORM_ASSOCIAR_USUARIO; 

	}
	

	public String associarUsuario() {
		try {
			if( ValidatorUtil.isEmpty(obj.getLogin() ) ) {
				addMensagemErro("Login: campo obrigat�rio n�o informado");
			}
			if( ValidatorUtil.isEmpty(obj.getSenha() ) ) {
				addMensagemErro("Senha: campo obrigat�rio n�o informado");
			}
			if( ValidatorUtil.isEmpty(obj.getSenhaConfirmacao() ) ){
				addMensagemErro("Confirma��o de Senha: campo obrigat�rio n�o informado");
			}
			
			if( isContemErros() ) {
				return FORM_ASSOCIAR_USUARIO;
			}
			service.executeAssociacao(obj);
		} catch (Exception e) {
			addMensagemErro( processaException(e) );
			return FORM_ASSOCIAR_USUARIO;
		}
		addMensagemInformacao("Opera��o efetuada com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	
	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
