package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Turma;

@Service
@Transactional
public class TurmaService extends AbstractService{
	public Turma executeCadastro( Turma turma) throws NegocioException {
		if( ValidatorUtil.isEmpty(turma) ) {
			cadastrar(turma);
		}
		else {
			alterar(turma);
		}
		return turma;
	}
}
