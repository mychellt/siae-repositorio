package br.siae.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.CadastroService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;

@Service
@Transactional
public class AlunoService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;

	public Aluno executarCadastro( Aluno aluno ) throws NegocioException, DAOException {
		GenericDAO dao = cadastroService.getGenericDAO();
		List<Pessoa> lista = (List<Pessoa>) dao.findByExactField( Pessoa.class, "cpf", aluno.getPessoa().getCpf() );
		if( ValidatorUtil.isEmpty(aluno) ){
			if( ValidatorUtil.isNotEmpty(lista)){
				throw new NegocioException("Já existe um aluno cadastrado com o CPF informado.");
			}
			aluno = (Aluno) cadastroService.cadastrar(aluno);
		}
		else {
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != aluno.getPessoa().getId() ) {
				throw new NegocioException("Já existe um aluno cadastrado com o CPF informado.");
			}
			aluno = (Aluno) cadastroService.alterar(aluno);
		}
		return aluno;
	}
}
