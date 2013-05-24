package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.dominio.academico.FormacaoAcademica;
import br.siae.dominio.academico.NivelFormacao;
import br.siae.service.FormacaoAcademicaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional 
public class FormacaoAcademicaTest {
	
	@Resource(name="formacaoAcademicaService")
	private FormacaoAcademicaService service;
	
	@Test
	public void testSave() {
		FormacaoAcademica formacao = new FormacaoAcademica();
		formacao.setDenominacao("Junit denominação");
		formacao.setNivel( service.getByPrimaryKey(NivelFormacao.class, NivelFormacao.GRADUACAO));
		try {
			service.executeCadastro(formacao);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(formacao.getId());
	}

}
