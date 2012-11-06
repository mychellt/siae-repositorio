package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Professor;

@Service
@Transactional
public class ProfessorService {
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;

	public Professor executarCadastro( Professor professor ) throws NegocioException, DAOException {
		//Persiste a pessoa
		pessoaService.executeCadastro( professor.getPessoa() );
		if( ValidatorUtil.isNotEmpty(professor) ){
			professor = (Professor) cadastroService.alterar(professor);
		}
		else {
			professor = (Professor) cadastroService.cadastrar(professor);
		}
		return professor;
	}
}
