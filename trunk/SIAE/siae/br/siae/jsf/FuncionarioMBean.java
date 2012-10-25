package br.siae.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.dominio.rh.Funcionario;

@Controller
@Scope("session")
public class FuncionarioMBean extends AbstractCrudController<Funcionario>{

	@Autowired
	private PessoaMBean pessoaMBean;
	
	public  FuncionarioMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Funcionario();
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Funcionário");
		pessoaMBean.setControlador(this);
		return super.iniciarCadastro();
	}
	
	public String cadastrar() {
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	
	public String getNomeObj() {
		return "Funcionário";
	}
}
