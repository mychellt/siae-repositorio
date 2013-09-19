package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.dominio.Logradouro;
import br.arq.dominio.Municipio;
import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.comum.Instituicao;


@Service
@Transactional
public class InstituicaoService extends AbstractService{
	
	public Instituicao executeCadastro( Instituicao instituicao ) throws NegocioException {
		instituicao.setModalidade( getByPrimaryKey(Nivel.class, instituicao.getModalidade().getId() ) );
		if( ValidatorUtil.isEmpty( instituicao.getEndereco().getLogradouro() ) ) {
			instituicao.getEndereco().setLogradouro(null);
		}
		if( ValidatorUtil.isEmpty( instituicao.getEndereco().getMunicipio()) ) {
			instituicao.getEndereco().setMunicipio(null);
		}
		if( ValidatorUtil.isEmpty(instituicao) ) {
			cadastrar(instituicao);
		}
		else {
			alterar(instituicao);
		}
		if( ValidatorUtil.isNotEmpty(( instituicao.getEndereco().getLogradouro() ) ) ) {
			instituicao.getEndereco().setLogradouro( getByPrimaryKey(Logradouro.class, instituicao.getEndereco().getLogradouro().getId() ) );
		}
		if( ValidatorUtil.isNotEmpty(( instituicao.getEndereco().getMunicipio() ) ) ) {
			instituicao.getEndereco().setMunicipio( getByPrimaryKey(Municipio.class, instituicao.getEndereco().getMunicipio().getId() ) );
		}
		instituicao.setModalidade( getByPrimaryKey(Nivel.class, instituicao.getModalidade().getId() ) );
		return instituicao;
	}
	
	public Instituicao executeRemocao( Instituicao instituicao ) throws NegocioException {
		remover(instituicao);
		return instituicao;
	}
}
