package br.siae.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.DisciplinaTurmaProfessor;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;

@Service
@Transactional
public class TurmaService extends AbstractService{
	public Turma executeCadastro( Turma turma) throws NegocioException {
		Collection<TurmaProfessor> professoresRemocao = turma.getProfessoresRemocao();
		Collection<TurmaProfessor> professores = turma.getProfessores();
		if( ValidatorUtil.isEmpty(turma) ) {
			cadastrar(turma);
		}
		else {
			//Efetua a remo��o das associa��es entre turma e processor que foram removidas durante a altera��o da turma.
			if( ValidatorUtil.isNotEmpty(professoresRemocao) ) {
				for( TurmaProfessor tp : professoresRemocao ) {
					if( ValidatorUtil.isNotEmpty(tp) ) {
						remover(tp);
					}
				}
			}
			//Efetua a remo��o das disciplinas que foram removidas durante a altera��o da turma.
			if( ValidatorUtil.isNotEmpty(professores) ) {
				for( TurmaProfessor tp : professores ) {
					Collection<DisciplinaTurmaProfessor> disciplinasRemoacao = tp.getDisciplinasRemoacao();
					for( DisciplinaTurmaProfessor dtp : disciplinasRemoacao ) {
						if( ValidatorUtil.isNotEmpty(dtp) ) {
							remover(dtp);
						}
					}
				}
			}
			alterar(turma);
		}
		return turma;
	}
	public Turma executeRemocao(Turma turma) throws NegocioException {
		return (Turma) remover(turma);
	}
}
