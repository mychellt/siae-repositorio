package br.arq.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.arq.seguranca.Permissao;
import br.arq.seguranca.Usuario;
import br.arq.service.UsuarioService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml" })  
@Transactional 
public class UsuarioTest extends TestCase{
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Resource(name="usuarioService")
	private UsuarioService service;
	
	@Test
	public void cadastro() {
		Usuario usuario = new Usuario();
		usuario.setLogin("LOGIN");
		usuario.setAtivo(true);
		usuario.setDataCadastro(new Date());
		usuario.setSenha("SENHA");
		usuario.setSenhaConfirmacao("SENHA");
		
		Permissao permissao = new Permissao();
		permissao.setDenominacao("HOLE_USUARIO");
		permissao.setDescricao("DESCRICAO_HOLE_USUARIO");
		
		List<Permissao> permissoes = new ArrayList<Permissao>();
		permissoes.add(permissao);
		
		try {
			service.executeAssociacao(usuario, permissoes);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}
