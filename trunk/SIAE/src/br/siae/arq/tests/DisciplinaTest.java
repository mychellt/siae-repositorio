package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.dominio.academico.Disciplina;
import br.siae.dominio.academico.Nivel;
import br.siae.service.DisciplinaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional  
public class DisciplinaTest {
	@Resource(name="disciplinaService")
	private DisciplinaService service;
	
	@Test  
	public void testSave() {  
		Disciplina disciplina = new Disciplina();
		disciplina.setNome("Nome da Disciplina");
		disciplina.setNivel( service.getByPrimaryKey(Nivel.class, Nivel.EDUCACAO_INFANTIL));
		disciplina.setReprova(true);
		disciplina.setSuplementar(false);
		disciplina.setSigla("NoDi");
		disciplina.setValorDependencia( new BigDecimal(25.0) );
		try {
			service.executeCadastro(disciplina);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(disciplina.getId());
	}  
}
