package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Professor;

@Service
@Transactional
public class ProfessorService extends AbstractService{
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;

	public Professor executarCadastro( Professor professor ) throws NegocioException, DAOException {
		//Persiste a pessoa
		pessoaService.executeCadastro( professor.getPessoa() );
		if( ValidatorUtil.isNotEmpty(professor) ){
			professor = (Professor) alterar(professor);
		}
		else {
			professor = (Professor) cadastrar(professor);
		}
		return professor;
	}
}
