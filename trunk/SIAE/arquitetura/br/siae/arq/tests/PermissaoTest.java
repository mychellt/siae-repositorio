package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.service.PermissaoService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml" })  
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
		} catch (NegocioException e) {
			assertNull(e);
			e.printStackTrace();
		} catch (DAOException e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(permissao.getId());
	}  
	  
	@Test  
	public void testDelete(){  
	}  
	  
	@Test  
	public void testUpdate(){ 
		
	}
}
