package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.CertidaoNascimento;
import br.siae.arq.dominio.CertificadoMilitar;
import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Identidade;
import br.siae.arq.dominio.Naturalidade;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.dominio.TituloEleitor;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.PessoaService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/WEB-INF/applicationContext.xml" })  
@Transactional  
public class PessoaTest {
	@Resource(name="pessoaService")
	private PessoaService service;
	
	private long id;
	
	@Test  
	public void testSave() {  
		Pessoa pessoa = new Pessoa();  
		pessoa.setNome("Pessoa Teste Unitário");
		pessoa.setEndereco( new Endereco() );
		pessoa.setIdentidade( new Identidade() );
		pessoa.setCertidaoNascimento( new CertidaoNascimento() );
		pessoa.setCertificadoMilitar( new CertificadoMilitar() );
		pessoa.setNaturalidade( new Naturalidade() );
		pessoa.setTituloEleitor( new TituloEleitor() );
		pessoa.setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		try {
			service.executeCadastro(pessoa);
		} catch (NegocioException e) {
			assertNull(e);
			e.printStackTrace();
		} catch (DAOException e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(pessoa.getId());
	}  
	  
	@Test  
	public void testDelete(){  
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		try {
			service.executeRemocao(pessoa);
		} catch (NegocioException e) {
			e.printStackTrace();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		assertNull( service.getCadastroService().getByPrimaryKey(Pessoa.class, id) );
	}  
	  
	@Test  
	public void testUpdate(){ 
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}  
}
