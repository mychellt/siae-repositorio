package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.utils.DAOUtils;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.comum.Instituicao;
import br.siae.service.InstituicaoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional 
public class InstituicaoTest {
	
	@Resource(name="instituicaoService")
	private InstituicaoService service;
	
	@Test  
	public void testSave() {  
		Instituicao instituicao = new Instituicao();
		instituicao.setCodigoInep("111111");
		instituicao.setEmail("junit@java.com");
		Endereco endereco =  new Endereco();
		endereco.setLogradouro( new Logradouro() );
		endereco.getLogradouro().setId( Logradouro.RUA );
		endereco.setDenominacao("Junit Endereço");
		instituicao.setEndereco(endereco);
		instituicao.setNome("Instituição Junit");
		instituicao.setModalidade( service.getByPrimaryKey(Nivel.class, Nivel.ENSINO_FUNDAMENTAL_I ));
		
		try {
			service.executeCadastro(instituicao);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(instituicao.getId());
	}
	
	@Test
	public void testUnicidade() {
		Instituicao instituicao = new Instituicao();
		instituicao.setCodigoInep("99999");
		instituicao.setEmail("junit@java.com");
		Endereco endereco =  new Endereco();
		endereco.setLogradouro( new Logradouro() );
		endereco.getLogradouro().setId( Logradouro.RUA );
		endereco.setDenominacao("Junit Endereço");
		instituicao.setEndereco(endereco);
		instituicao.setNome("Instituição Junit");
		instituicao.setModalidade( service.getByPrimaryKey(Nivel.class, Nivel.ENSINO_FUNDAMENTAL_I ));
		
		
		Instituicao instituicaoRepetido = new Instituicao();
		instituicaoRepetido.setCodigoInep("111111");
		instituicaoRepetido.setEmail("junit@java.com");
		Endereco enderecoIR =  new Endereco();
		enderecoIR.setLogradouro( new Logradouro() );
		enderecoIR.getLogradouro().setId( Logradouro.RUA );
		enderecoIR.setDenominacao("Junit Endereço");
		instituicaoRepetido.setEndereco(endereco);
		instituicaoRepetido.setNome("Instituição Junit II");
		instituicaoRepetido.setModalidade( service.getByPrimaryKey(Nivel.class, Nivel.ENSINO_FUNDAMENTAL_I ));
		
		Exception exception = null;
		try {
			service.executeCadastro(instituicao);
			service.executeCadastro(instituicaoRepetido);
		} catch (Exception e) {
			exception = e;
			if( DAOUtils.isUniqueConstraintErro(e) ) {
				assertNull(e);
			}
			e.printStackTrace();
		}
		finally {
			assertNotNull(exception);
		}
	}
}
