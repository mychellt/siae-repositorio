package br.siae.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dao.AlunoDAO;
import br.siae.dominio.academico.Aluno;

@Service
@Transactional
public class AlunoService extends AbstractService{
	@Resource(name="alunoDAO")
	private AlunoDAO dao;
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	public Aluno executeCadastro( Aluno aluno ) throws NegocioException {
		//Persiste a pessoa
		pessoaService.executeCadastro( aluno.getPessoa() );
		if( ValidatorUtil.isNotEmpty(aluno) ){
			aluno = (Aluno) alterar(aluno);
		}
		else {
			aluno = (Aluno) cadastrar(aluno);
		}
		return aluno;
	}
	
	public Collection<Aluno> getByCriteriosBusca(Aluno aluno) throws NegocioException{
		return dao.findByCriterios( ValidatorUtil.isNotEmpty( aluno.getPessoa().getNome() ) ? aluno.getPessoa().getNome() : null, 
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getNomeMae() ) ? aluno.getPessoa().getNomeMae() : null, 
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getNomePai() ) ? aluno.getPessoa().getNomePai(): null,
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getCpf() ) ? aluno.getPessoa().getCpf() : null, 
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getDataNascimento() ) ? aluno.getPessoa().getDataNascimento() : null, 
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getIdentidade().getNumero() ) ? aluno.getPessoa().getIdentidade().getNumero() : null, 
									ValidatorUtil.isNotEmpty( aluno.getPessoa().getCertidaoNascimento().getNumero() ) ? aluno.getPessoa().getCertidaoNascimento().getNumero() : null ) ;
	}

	public List<Aluno> getAll() throws NegocioException{
		return (List<Aluno>) dao.findAll( Aluno.class );
	}
	
	public Aluno executeRemocao( Aluno aluno ) throws NegocioException  {
		return (Aluno) remover(aluno);
	}
}
