package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;

@Service
@Transactional
public class AlunoService {
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;

	public Aluno executarCadastro( Aluno aluno ) throws NegocioException, DAOException {
		//Persiste a pessoa
		pessoaService.executarCadastro( aluno.getPessoa() );
		if( ValidatorUtil.isNotEmpty(aluno) ){
			aluno = (Aluno) cadastroService.alterar(aluno);
		}
		else {
			aluno = (Aluno) cadastroService.cadastrar(aluno);
		}
		return aluno;
	}
}
