package br.siae.matricula.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dao.AlunoDAO;
import br.siae.dominio.academico.Aluno;

@Service
@Transactional
public class ConsultaAlunoService extends AbstractService{
	
	@Resource(name="alunoDAO")
	private AlunoDAO dao;
	
	public Collection<Aluno> busca(Aluno aluno) throws NegocioException {
		List<Aluno> colecao = dao.findByCriterios(ValidatorUtil.isNotEmpty(aluno.getPessoa().getNome()) ? aluno.getPessoa().getNome() : null, 
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getNomeMae()) ? aluno.getPessoa().getNomeMae() : null, 
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getNomePai()) ? aluno.getPessoa().getNomePai() : null, 
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getCpf()) ? aluno.getPessoa().getCpf() : null, 
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getDataNascimento()) ? aluno.getPessoa().getDataNascimento() : null,
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getIdentidade().getNumero()) ? aluno.getPessoa().getIdentidade().getNumero() : null,
							ValidatorUtil.isNotEmpty(aluno.getPessoa().getCertidaoNascimento().getNumero()) ?  aluno.getPessoa().getCertidaoNascimento().getNumero() : null);
		return colecao;
		
	}
	
}
