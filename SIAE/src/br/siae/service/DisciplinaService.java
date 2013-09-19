package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Disciplina;

@Service
@Transactional
public class DisciplinaService extends AbstractService{
	public Disciplina executeCadastro( Disciplina disciplina ) throws NegocioException {
		if( ValidatorUtil.isEmpty(disciplina) ) {
			cadastrar(disciplina);
		}
		else {
			alterar(disciplina);
		}
		return disciplina;
	}
	public Disciplina executeRemocao( Disciplina disciplina ) throws NegocioException {
		disciplina = (Disciplina) remover(disciplina);
		return disciplina;
	}
}
