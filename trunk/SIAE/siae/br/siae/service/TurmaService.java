package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.dominio.academico.Turma;

@Service
@Transactional
public class TurmaService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Turma executarCadastro( Turma turma ) throws NegocioException {
		return turma;
	}

}
