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

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
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
	@ExpectedException(ObjectDeletedException.class)
	public void testDelete(){
		Permissao p = new Permissao();
		p.setId(2);
		try {
			service.executeRemocao(p);
		} catch (NegocioException e) {
			assertNull(e);
			e.printStackTrace();
		} catch (DAOException e) {
			assertNull(e);
			e.printStackTrace();
		}
		Permissao permissao = service.getByPrimaryKey(Permissao.class, p.getId() );
		assertNull( permissao );
		
	}  
	  
	@Test  
	public void testUpdate(){ 
		Permissao permissao = new Permissao();
		permissao.setDenominacao("ROLE_TESTE");
		permissao.setDescricao("Permissão para teste unitário");
		try {
			permissao = (Permissao) service.cadastrar(permissao);
			String denominacao = permissao.getDenominacao();
			permissao.setDenominacao("ROLE_TESTE_ALTERADO");
			service.alterar(permissao);
			assertNotSame(denominacao, permissao.getDenominacao());
			
		} catch (DAOException e) {
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
		} catch (DAOException e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(lista);
	}
}
