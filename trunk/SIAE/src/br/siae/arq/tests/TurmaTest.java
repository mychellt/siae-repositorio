package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.comum.Turno;
import br.siae.dominio.rh.Professor;
import br.siae.service.TurmaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional
public class TurmaTest {
	@Resource(name="turmaService")
	private TurmaService service;
	
	@Test  
	public void testSave() {  
		Turma turma = new Turma();
		turma.setAno(2012);
		turma.setNome("Nome da Turma");
		turma.setNumeroVagas(20);
		turma.setSerie( service.getByPrimaryKey(Serie.class, 45));
		turma.setTurno( service.getByPrimaryKey(Turno.class, Turno.MANHA));
	
		Collection<Professor> professores = new ArrayList<Professor>();
		professores.add( service.getByPrimaryKey(Professor.class, 8));
		professores.add( service.getByPrimaryKey(Professor.class, 9));
		professores.add( service.getByPrimaryKey(Professor.class, 10));
	
		try {
			service.executeCadastro(turma, new ArrayList<Professor>() );
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(turma.getId());
		assertTrue( ValidatorUtil.isNotEmpty(turma.getProfessores()));
		for( TurmaProfessor tp : turma.getProfessores() ) {
			assertNotNull(tp.getId());
		}
	}  
}
