package br.arq.test;

import java.util.Collection;

import javax.annotation.Resource;

import junit.framework.TestCase;

import org.hibernate.ObjectDeletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.arq.seguranca.Permissao;
import br.arq.service.PermissaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml" })  
@Transactional  
public class PermissaoTest extends TestCase{
	@Resource(name="permissaoService")
	private PermissaoService service;
	
	@Test  
	public void testSave() {  
		Permissao permissao = new Permissao();
		permissao.setDenominacao("DENOMINACAO_PERMISSAO");
		permissao.setDescricao("DESCRICAO_PERMISSAO");
		try {
			service.executeCadastro(permissao);
		} catch (Exception e) {
			assertNull(e);
		}	
		assertNotNull(permissao.getId());
	}  
	  
	@Test
	public void testDelete(){
		Permissao permissao = new Permissao();
		permissao.setDenominacao("DENOMINACAO_PERMISSAO");
		permissao.setDescricao("DESCRICAO_PERMISSAO");
		try {
			Permissao permissaoDB = service.executeCadastro(permissao);
			service.executeRemocao(permissaoDB);
		} catch (Exception e) {
			assertNull(e);
		}
		try  {
			service.getByPrimaryKey(Permissao.class, permissao.getId() );
		}
		catch (Exception e) {
			if(e instanceof ObjectDeletedException ) {
				assertNotNull(e);
			}
		}
	}  
	  
	@Test  
	public void testUpdate(){ 
		Permissao permissao = new Permissao();
		permissao.setDenominacao("DENOMINACAO_PERMISSAO");
		permissao.setDescricao("DESCRICAO_PERMISSAO");
		try {
			Permissao permissaoDB = service.executeCadastro(permissao);
			permissao = (Permissao) service.getByPrimaryKey(Permissao.class, permissaoDB.getId());
			String denominacao = permissao.getDenominacao();
			permissao.setDenominacao("DENOMINACAO_PERMISSAO_ALTERACAO");
			service.alterar(permissao);
			assertNotSame(denominacao, permissao.getDenominacao());
			
		} catch (Exception e) {
			assertNull( e );
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
		}
		assertNotNull(lista);
	}
}
