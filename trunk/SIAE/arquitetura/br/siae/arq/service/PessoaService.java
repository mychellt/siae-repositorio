package br.siae.arq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dao.PessoaDAO;
import br.siae.arq.dominio.Estado;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PessoaService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	@Resource(name="pessoaDAO")
	private PessoaDAO pessoaDAO;
	
	public Pessoa executeCadastro( Pessoa pessoa ) throws NegocioException, DAOException {
		GenericDAO dao = cadastroService.getGenericDAO();
		List<Pessoa> lista = (List<Pessoa>) dao.findByExactField( Pessoa.class, "cpf", pessoa.getCpf() );
		if( ValidatorUtil.isEmpty( pessoa.getEndereco().getMunicipio())) {
			pessoa.getEndereco().setMunicipio(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getEndereco().getLogradouro())) {
			pessoa.getEndereco().setLogradouro(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getNaturalidade().getEstado() ) ) {
			pessoa.getNaturalidade().setEstado(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getNaturalidade().getPais() ) ) {
			pessoa.getNaturalidade().setPais(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getNaturalidade().getMunicipio()) ) {
			pessoa.getNaturalidade().setMunicipio(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getTituloEleitor().getUf() ) ) {
			pessoa.getTituloEleitor().setUf(null);
		}
		if( ValidatorUtil.isEmpty( pessoa.getIdentidade().getEstado() ) ){
			pessoa.getIdentidade().setEstado(null);
		}
		if( ValidatorUtil.isEmpty(pessoa) ){
			if( ValidatorUtil.isNotEmpty(lista)){
				throw new NegocioException("Já existe um aluno cadastrado com o CPF informado.");
			}
			pessoa = (Pessoa) cadastroService.cadastrar(pessoa);
		}
		else {
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != pessoa.getId() ) {
				throw new NegocioException("Já existe um aluno cadastrado com o CPF informado.");
			}
			pessoa = (Pessoa) cadastroService.alterar(pessoa);
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getIdentidade().getEstado() ) ) {
			pessoa.getIdentidade().setEstado( dao.findByPrimaryKey(Estado.class, pessoa.getIdentidade().getEstado().getId() ) );
		}
		return pessoa;
	}
	
	public Pessoa getByCpf( long cpf ) throws DAOException {
		return pessoaDAO.findByCpf(cpf);
	}
	
	public Pessoa executeRemocao( Pessoa pessoa ) throws NegocioException, DAOException {
		return (Pessoa) cadastroService.remover(pessoa);
	}
	
	public CadastroService getCadastroService() {
		return cadastroService;
	}
}
