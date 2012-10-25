package br.siae.jsf;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.dominio.academico.ProfessorDisciplina;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.rh.Professor;

@Controller
@Scope("session")
public class ProfessorMBean extends AbstractCrudController<Professor>{
	
	@Autowired
	private PessoaMBean pessoaMBean;
	
	public ProfessorMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Professor();
		obj.setDisciplinas( new ArrayList<ProfessorDisciplina>() );
		obj.setTurmas( new ArrayList<TurmaProfessor>() );
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastrar Professor");
		pessoaMBean.setControlador(this);
		return super.iniciarCadastro();
	}
	
	public String cadastrar() {
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	
	public String getNomeObj() {
		return "Professor";
	}
}
