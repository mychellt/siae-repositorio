package br.siae.arq.tests;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import java.util.Date;

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
import br.siae.arq.dominio.Sexo;
import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.dominio.TituloEleitor;
import br.siae.dominio.comum.Instituicao;
import br.siae.dominio.rh.Funcionario;
import br.siae.service.FuncionarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WebContent/META-INF/applicationContext.xml" })  
@Transactional 
public class FuncionarioTest {
	@Resource(name="funcionarioService")
	private FuncionarioService service;
	
	@Test  
	public void testSave() {  
		Funcionario funcionario = new Funcionario();
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Nome do professor");
		pessoa.setDataNascimento( new Date() );
		pessoa.setNomeMae("Mão do professor");
		pessoa.setSexo( service.getByPrimaryKey(Sexo.class, Sexo.MASCULINO ) );
		pessoa.setEndereco( new Endereco() );
		pessoa.setNaturalidade( new Naturalidade() );
		pessoa.setCertidaoNascimento( new CertidaoNascimento() );
		pessoa.setCertificadoMilitar( new CertificadoMilitar() );
		pessoa.setIdentidade( new Identidade() );
		pessoa.setTipo( service.getByPrimaryKey(TipoPessoa.class, TipoPessoa.PESSOA_FISICA ) );
		pessoa.setTituloEleitor( new TituloEleitor() );
		
		
		funcionario.setPessoa(pessoa);
		funcionario.setLotacao( service.getByPrimaryKey(Instituicao.class, 40));
	
		try {
			service.executeCadastro(funcionario);
		} catch (Exception e) {
			assertNull(e);
			e.printStackTrace();
		}
		assertNotNull(funcionario.getId());
		assertNotNull(pessoa.getId());
	}  
}
