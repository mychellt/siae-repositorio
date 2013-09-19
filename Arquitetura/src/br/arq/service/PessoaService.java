package br.arq.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dao.PessoaDAO;
import br.arq.dominio.Estado;
import br.arq.dominio.Logradouro;
import br.arq.dominio.Municipio;
import br.arq.dominio.Pais;
import br.arq.dominio.Pessoa;
import br.arq.dominio.Sexo;
import br.arq.erros.NegocioException;
import br.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PessoaService extends AbstractService{
	
	@Resource(name="pessoaDAO")
	private PessoaDAO pessoaDAO;
	
	
	
	public Pessoa executeCadastro( Pessoa pessoa ) throws NegocioException {
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
			pessoa = (Pessoa) cadastrar(pessoa);
		}
		else {
			pessoa = (Pessoa) alterar(pessoa);
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getIdentidade().getEstado() ) ) {
			pessoa.getIdentidade().setEstado( getGenericDAO().findByPrimaryKey(Estado.class, pessoa.getIdentidade().getEstado().getId() ) );
		}
		Logradouro logradouro = pessoa.getEndereco().getLogradouro();
		if( ValidatorUtil.isNotEmpty( logradouro ) ) {
			pessoa.getEndereco().setLogradouro( getGenericDAO().findByPrimaryKey(Logradouro.class, logradouro.getId() ) );
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getTituloEleitor().getUf() ) ) {
			pessoa.getTituloEleitor().setUf( getGenericDAO().findByPrimaryKey( Estado.class, pessoa.getTituloEleitor().getUf().getId() ) );
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getNaturalidade().getPais() ) ) {
			pessoa.getNaturalidade().setPais( getGenericDAO().findByPrimaryKey(Pais.class, pessoa.getNaturalidade().getPais().getId() ) );
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getNaturalidade().getMunicipio() ) ) {
			pessoa.getNaturalidade().setMunicipio( getGenericDAO().findByPrimaryKey(Municipio.class, pessoa.getNaturalidade().getMunicipio().getId()) );
		}
		if( ValidatorUtil.isNotEmpty( pessoa.getEndereco().getMunicipio() ) ) {
			pessoa.getEndereco().setMunicipio( getGenericDAO().findByPrimaryKey(Municipio.class, pessoa.getEndereco().getMunicipio().getId()) );
		}
		pessoa.setSexo( getGenericDAO().findByPrimaryKey(Sexo.class, pessoa.getSexo().getId() ) );
		return pessoa;
	}
	
	public Pessoa getByCpf( long cpf ) throws NegocioException {
		return pessoaDAO.findByCpf(cpf);
	}
	
	public Pessoa executeRemocao( Pessoa pessoa ) throws NegocioException {
		return (Pessoa) remover(pessoa);
	}
	
}
