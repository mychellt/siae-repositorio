package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import javax.annotation.Resource;

import org.hibernate.ObjectDeletedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.service.CargoService;
import br.siae.dominio.rh.Cargo;
import br.siae.dominio.rh.Categoria;
import br.siae.dominio.rh.NivelFuncional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional 
public class CargoTest {

	@Resource(name="cargoService")
	private CargoService service;
	
	@Test
	public void testSave() {
		Cargo cargo = new Cargo();
		cargo.setDenominacao("Junit denominação");
		cargo.setCategoria( service.getByPrimaryKey(Categoria.class, Categoria.DOCENTE));
		cargo.setNivelFuncional( service.getByPrimaryKey(NivelFuncional.class, NivelFuncional.SUPERIOR));

		try {
			service.executeCadastro(cargo);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(cargo.getId());
	}
	
	@Test
	public void testDelete(){
		Cargo cargo = new Cargo();
		cargo.setDenominacao("Junit denominação");
		cargo.setCategoria( service.getByPrimaryKey(Categoria.class, Categoria.DOCENTE));
		cargo.setNivelFuncional( service.getByPrimaryKey(NivelFuncional.class, NivelFuncional.SUPERIOR));
		try {
			cargo = (Cargo) service.cadastrar(cargo);
			service.executeRemocao(cargo);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		try  {
			service.getByPrimaryKey(Cargo.class, cargo.getId() );
		}
		catch (Exception e) {
			if(e instanceof ObjectDeletedException ) {
				assertNotNull(e);
			}
		}
	}
}
