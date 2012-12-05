package br.siae.service;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dao.ProfessorDAO;
import br.siae.dominio.comum.Instituicao;
import br.siae.dominio.rh.Professor;

@Service
@Transactional
public class ProfessorService extends AbstractService{
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="professorDAO")
	private ProfessorDAO professorDAO;

	public Professor executeCadastro( Professor professor ) throws NegocioException {
		//Persiste a pessoa
		pessoaService.executeCadastro( professor.getPessoa() );
		if( ValidatorUtil.isNotEmpty(professor) ){
			professor = (Professor) alterar(professor);
		}
		else {
			professor = (Professor) cadastrar(professor);
		}
		professor.setLotacao( getByPrimaryKey(Instituicao.class, professor.getLotacao().getId() ) );
		return professor;
	}

	public Collection<Professor> getByNome( String nome ) throws NegocioException {
		return professorDAO.findByNome(nome);
	}
	public Professor getByPessoa( Pessoa pessoa ) throws NegocioException {
		return professorDAO.findByPessoa(pessoa);
	}
}
