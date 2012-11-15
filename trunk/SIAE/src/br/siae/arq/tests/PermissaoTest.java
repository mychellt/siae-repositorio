package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.ObjectDeletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.seguranca.Permissao;
import br.siae.arq.service.PermissaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional  
public class PermissaoTest {
	@Resource(name="permissaoService")
	private PermissaoService service;
	
	@Test  
	public void testSave() {  
		Permissao permissao = new Permissao();
		permissao.setDenominacao("ROLE_TESTE");
		permissao.setDescricao("Permissão de Testes Unitário");
		try {
			service.executeCadastro(permissao);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(permissao.getId());
	}  
	  
	@Test
	@ExpectedException(ObjectDeletedException.class)
	public void testDelete(){
		Permissao permissao = new Permissao();
		try {
			permissao = service.getByPrimaryKey(Permissao.class, 2);
			service.executeRemocao(permissao);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNull( service.getByPrimaryKey(Permissao.class, permissao.getId() ) );
		
	}  
	  
	@Test  
	public void testUpdate(){ 
		Permissao permissao = new Permissao();
		try {
			permissao = (Permissao) service.getByPrimaryKey(Permissao.class, 2);
			String denominacao = "ROLE_TESTE_ALTERADO";
			permissao.setDenominacao(denominacao);
			service.alterar(permissao);
			assertNotSame(denominacao, permissao.getDenominacao());
			
		} catch (Exception e) {
			assertNull( e );
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFind() {
		Permissao permissao = new Permissao();
		permissao.setDenominacao("ROLE_ADMIN");
		Collection<Permissao> lista = null;
		try {
			lista = service.getByExactField(Permissao.class, "denominacao", permissao.getDenominacao() );
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(lista);
	}
}
