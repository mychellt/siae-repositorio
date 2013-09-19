package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dominio.Pessoa;
import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.service.PessoaService;
import br.arq.utils.ValidatorUtil;
import br.siae.dao.FuncionarioDAO;
import br.siae.dominio.rh.Funcionario;

@Service
@Transactional
public class FuncionarioService extends AbstractService{
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="funcionarioDAO")
	private FuncionarioDAO funcionarioDAO;
	
	public Funcionario executeCadastro( Funcionario funcionario ) throws NegocioException {
		//Persiste a pessoa
		pessoaService.executeCadastro( funcionario.getPessoa() );
		if( ValidatorUtil.isNotEmpty(funcionario) ){
			funcionario = (Funcionario) alterar(funcionario);
		}
		else {
			funcionario = (Funcionario) cadastrar(funcionario);
		}
		return funcionario;
	}
	public Funcionario getByPessoa( Pessoa pessoa ) throws NegocioException {
		return funcionarioDAO.findByPessoa(pessoa);
	}
}
