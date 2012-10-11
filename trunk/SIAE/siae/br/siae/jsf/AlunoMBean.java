package br.siae.jsf;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Identidade;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.Usuario;
import br.siae.arq.jsf.AbstractCrudController;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.ElementoMatricula;

@Controller
@Scope("session")
public class AlunoMBean extends AbstractCrudController<Aluno>{

	
	public AlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
		obj.setMae( new Pessoa() );
		obj.setPai( new Pessoa() );
		obj.setPessoa( new Pessoa() );
		obj.setElementoMatricula( new ElementoMatricula() );
		obj.setResponsavel( new Pessoa() );
		obj.setUsuario( new Usuario() );
		obj.getPessoa().setEndereco( new Endereco() );
		obj.getPessoa().setIdentidade( new Identidade() );
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		return super.iniciarCadastro();
	}
}
