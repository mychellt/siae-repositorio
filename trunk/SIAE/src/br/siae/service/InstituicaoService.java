package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
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
}
