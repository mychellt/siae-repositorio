package br.siae.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.utils.ValidatorUtil;
import br.siae.dao.TurmaDAO;
import br.siae.dominio.academico.DisciplinaTurmaProfessor;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.academico.TurmaProfessor;

@Service
@Transactional
public class TurmaService extends AbstractService{
	@Resource(name="turmaDAO")
	private TurmaDAO dao;
	
	public Turma executeCadastro( Turma turma) throws NegocioException {
		Collection<TurmaProfessor> professoresRemocao = turma.getProfessoresRemocao();
		Collection<TurmaProfessor> professores = turma.getProfessores();
		if( ValidatorUtil.isEmpty(turma) ) {
			cadastrar(turma);
		}
		else {
			//Efetua a remoção das associações entre turma e processor que foram removidas durante a alteração da turma.
			if( ValidatorUtil.isNotEmpty(professoresRemocao) ) {
				for( TurmaProfessor tp : professoresRemocao ) {
					if( ValidatorUtil.isNotEmpty(tp) ) {
						remover(tp);
					}
				}
			}
			//Efetua a remoção das disciplinas que foram removidas durante a alteração da turma.
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
	
	public List<Turma> getByCriterios(Turma turma) {
		return dao.findByCriterios(ValidatorUtil.isNotEmpty(turma.getNome()) ? turma.getNome() : null,
								   ValidatorUtil.isNotEmpty(turma.getSerie()) ? turma.getSerie() : null,
								   ValidatorUtil.isNotEmpty(turma.getTurno()) ? turma.getTurno() : null,
								   ValidatorUtil.isNotEmpty(turma.getAno()) ?  new Integer(turma.getAno()) : null);
	}
}
