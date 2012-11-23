package br.siae.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;
import br.siae.dominio.rh.Professor;

@Service
@Transactional
public class TurmaService extends AbstractService{
	public Turma executeCadastro( Turma turma, Collection<Professor> professores ) throws NegocioException {
		if( ValidatorUtil.isEmpty(turma) ) {
			turma.setProfessores( new ArrayList<TurmaProfessor>() );
			for( Professor professor : professores ){
				TurmaProfessor tp = new TurmaProfessor();
				tp.setProfessor(professor);
				tp.setTurma(turma);
				turma.getProfessores().add(tp);
			}
			cadastrar(turma);
		}
		else {
			alterar(turma);
		}
		return turma;
	}
}
