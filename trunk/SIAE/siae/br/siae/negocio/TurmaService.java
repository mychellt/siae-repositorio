package br.siae.negocio;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.dominio.academico.Turma;

@Service
public class TurmaService {
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Turma executarCadastro( Turma turma ) throws NegocioException {
		return turma;
	}

}
