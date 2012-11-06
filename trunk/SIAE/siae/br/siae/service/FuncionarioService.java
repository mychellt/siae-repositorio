package br.siae.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Funcionario;

@Service
@Transactional
public class FuncionarioService {
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;

	public Funcionario executarCadastro( Funcionario funcionario ) throws NegocioException, DAOException {
		//Persiste a pessoa
		pessoaService.executeCadastro( funcionario.getPessoa() );
		if( ValidatorUtil.isNotEmpty(funcionario) ){
			funcionario = (Funcionario) cadastroService.alterar(funcionario);
		}
		else {
			funcionario = (Funcionario) cadastroService.cadastrar(funcionario);
		}
		return funcionario;
	}
}
