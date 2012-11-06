package br.siae.arq.service;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.utils.ValidatorUtil;

@Service
@Transactional
public class PermissaoService{
	
	@Resource(name="cadastroService")
	private CadastroService cadastroService;
	
	public Permissao executeCadastro( Permissao permissao ) throws NegocioException, DAOException {
		List<Permissao> lista = (List<Permissao>) cadastroService.getGenericDAO().findByLikeField(Permissao.class, "denominacao", permissao.getDenominacao() );
		if( ValidatorUtil.isEmpty( permissao ) ){
			if( ValidatorUtil.isNotEmpty(lista) ){
				throw new NegocioException("Já existe uma permissão cadastrada com essa denominação.");
			}
			permissao = (Permissao) cadastroService.cadastrar(permissao);
		}
		else {
			
			if( ValidatorUtil.isNotEmpty(lista) && lista.get(0).getId() != permissao.getId() ) {
				throw new NegocioException("Já existe uma permissão cadastrada com essa denominação.");
			}
			permissao = (Permissao) cadastroService.alterar(permissao);
		}
		return permissao;
	}
	
	
	public Permissao executeRemocao( Permissao permissao ) throws NegocioException, DAOException {
		permissao = (Permissao) cadastroService.remover(permissao);
		return permissao;
	}
	
	public Collection<Permissao> getAll() {
		return cadastroService.getGenericDAO().findAll( Permissao.class );
	}
}
