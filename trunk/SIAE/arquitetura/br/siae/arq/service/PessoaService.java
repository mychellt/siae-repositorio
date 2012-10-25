package br.siae.arq.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PessoaService {
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Pessoa executarCadastro( Pessoa pessoa ) throws NegocioException, DAOException {
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
				throw new NegocioException("J� existe um aluno cadastrado com o CPF informado.");
			}
			pessoa = (Pessoa) cadastroService.cadastrar(pessoa);
		}
		else {
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != pessoa.getId() ) {
				throw new NegocioException("J� existe um aluno cadastrado com o CPF informado.");
			}
			pessoa = (Pessoa) cadastroService.alterar(pessoa);
		}
		return pessoa;
	}
}
