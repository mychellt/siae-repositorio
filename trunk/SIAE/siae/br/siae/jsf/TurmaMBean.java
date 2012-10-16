package br.siae.jsf;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Controller;

import br.siae.arq.jsf.AbstractCrudController;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.comum.Turno;

@Controller
@Scope("session")
public class TurmaMBean extends AbstractCrudController<Turma>{
	public TurmaMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
		obj.setNome( new String() );
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		return super.iniciarCadastro();
	}
}
