package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertNull;

import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.ObjectDeletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.utils.DAOUtils;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.academico.Serie;
import br.siae.service.SerieService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional 
public class SerieTest {
	@Resource(name="serieService")
	private SerieService service;
	
	@Test  
	public void testSave() {  
		Serie serie = new Serie();
		serie.setDenominacao("JUNIT SÉRIE");
		serie.setNivel( new Nivel() );
		serie.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		try {
			service.executeCadastro(serie);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(serie.getId());
	}  
	  
	@Test
	public void testDelete(){
		Serie serie = new Serie();
		serie.setDenominacao("JUNIT SÉRIE");
		serie.setNivel( new Nivel() );
		serie.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		try {
			serie = (Serie) service.cadastrar(serie);
			service.executeRemocao(serie);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		try  {
			service.getByPrimaryKey(Serie.class, serie.getId() );
		}
		catch (Exception e) {
			if(e instanceof ObjectDeletedException ) {
				assertNotNull(e);
			}
		}
		
		
	}  
	  
	@Test  
	public void testUpdate(){ 
		Serie serie = new Serie();
		serie.setDenominacao("JUNIT SÉRIE");
		serie.setNivel( new Nivel() );
		serie.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		try {
			serie = (Serie) service.cadastrar(serie);
			String denominacao = serie.getDenominacao();
			serie.setDenominacao("JUNIT SÉRIE ALTERADA ");
			service.alterar(serie);
			assertNotSame(denominacao, serie.getDenominacao());
			
		} catch (Exception e) {
			assertNull( e );
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFind() {
		Serie serie = new Serie();
		serie.setDenominacao("JUNIT SÉRIE");
		serie.setNivel( new Nivel() );
		serie.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		Collection<Serie> lista = null;
		try {
			serie = (Serie) service.cadastrar(serie);
			lista = service.getByExactField(Serie.class, "denominacao", serie.getDenominacao() );
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(lista);
	}
	
	@Test
	public void testUnicidade() {
		Serie serie = new Serie();
		serie.setDenominacao("JUNIT SÉRIE");
		serie.setNivel( new Nivel() );
		serie.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		
		Serie serieRepetida = new Serie();
		serieRepetida.setDenominacao("JUNIT SÉRIE");
		serieRepetida.setNivel( new Nivel() );
		serieRepetida.getNivel().setId( Nivel.ENSINO_FUNDAMENTAL_I );
		
		Exception verificacao = null;
		
		try {
			service.cadastrar(serie);
			service.cadastrar(serieRepetida);
		}
		catch (Exception e) {
			verificacao = e;
			if( DAOUtils.isUniqueConstraintErro(e) ) {
				assertNull(e);
			}
			e.printStackTrace();
		}
		finally {
			assertNotNull(verificacao);
		}
	}
}
