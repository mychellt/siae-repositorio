package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Funcionario;

@Service
@Transactional
public class FuncionarioService extends AbstractService{
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public Funcionario executarCadastro( Funcionario funcionario ) throws NegocioException {
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
}
