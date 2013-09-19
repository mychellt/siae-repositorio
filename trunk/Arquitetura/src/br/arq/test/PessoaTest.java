package br.arq.test;

import javax.annotation.Resource;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dominio.CertidaoNascimento;
import br.arq.dominio.CertificadoMilitar;
import br.arq.dominio.Endereco;
import br.arq.dominio.Identidade;
import br.arq.dominio.Municipio;
import br.arq.dominio.Naturalidade;
import br.arq.dominio.Pessoa;
import br.arq.dominio.TipoPessoa;
import br.arq.dominio.TituloEleitor;
import br.arq.service.PessoaService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml" })  
@Transactional  
public class PessoaTest extends TestCase{
	@Resource(name="pessoaService")
	private PessoaService service;
	
	@Test  
	public void testSave() {  
		Pessoa pessoa = new Pessoa();  
		pessoa.setNome("NOME_PESSOA");
		pessoa.setEndereco( new Endereco() );
		//Municipio id = 5577 -> Natal
		pessoa.getEndereco().setMunicipio(service.getByPrimaryKey(Municipio.class, 5577));
		pessoa.setCpf( new Long("123456789"));
		pessoa.setIdentidade( new Identidade() );
		pessoa.setCertidaoNascimento( new CertidaoNascimento() );
		pessoa.setCertificadoMilitar( new CertificadoMilitar() );
		pessoa.setNaturalidade( new Naturalidade() );
		pessoa.setTituloEleitor( new TituloEleitor() );
		pessoa.setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		try {
			service.executeCadastro(pessoa);
		} catch (Exception e) {
			assertNull(e);
		}
		assertNotNull(pessoa.getId());
	}  
	  
	@Test  
	public void testDelete(){  
		Pessoa pessoa = new Pessoa();  
		pessoa.setNome("NOME_PESSOA");
		pessoa.setEndereco( new Endereco() );
		//Municipio id = 5577 -> Natal
		pessoa.getEndereco().setMunicipio(service.getByPrimaryKey(Municipio.class, 5577));
		pessoa.setCpf( new Long("123456789"));
		pessoa.setIdentidade( new Identidade() );
		pessoa.setCertidaoNascimento( new CertidaoNascimento() );
		pessoa.setCertificadoMilitar( new CertificadoMilitar() );
		pessoa.setNaturalidade( new Naturalidade() );
		pessoa.setTituloEleitor( new TituloEleitor() );
		pessoa.setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		try {
			service.executeCadastro(pessoa);
			service.executeRemocao(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		try {
			assertNull( service.getByPrimaryKey(Pessoa.class, pessoa.getId()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}  
	  
	@Test  
	public void testUpdate(){ 
		
	}
}
