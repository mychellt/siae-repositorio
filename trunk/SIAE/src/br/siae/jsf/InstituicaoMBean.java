package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.dominio.Endereco;
import br.arq.dominio.Municipio;
import br.arq.erros.ArqException;
import br.arq.erros.NegocioException;
import br.arq.service.MunicipioService;
import br.arq.siae.jsf.AbstractSiaeController;
import br.arq.utils.ArqCache;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.comum.Instituicao;
import br.siae.service.InstituicaoService;
import br.siae.utils.SIAECache;

@Controller
@Scope("session")
public class InstituicaoMBean extends AbstractSiaeController<Instituicao> implements ArqException{
	
	@Resource(name="municipioService")
	private MunicipioService municipioService;
	
	@Resource(name="instituicaoService")
	private InstituicaoService service;
	
	private Instituicao instituicaoVisualizacao;
	
	private Instituicao instituicaoRemocao;
	
	/** Municípios para o endereço da pessoa. */
	private Collection<Municipio> municipiosEndereco;
	
	public InstituicaoMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new Instituicao();
		obj.setEndereco( new Endereco() );
		obj.setModalidade( new Nivel() );
	}
	
	public String iniciarListagem() {
		resetObj();
		instituicaoVisualizacao = new Instituicao();
		try {
			lista = (List<Instituicao>) service.getAll( Instituicao.class );
		} catch (NegocioException e) {
			addMensagemErro( processaException(e) );
		}
		return getPaginaListagem();
	}
	
	public String iniciarCadastro()  {
		resetObj();
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		validar();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		try {
			service.executeCadastro(obj);
		} catch (Exception e) {
			addMensagemErro(processaException(e));
			return getPaginaCadastro();
		}
		addMensagemInformacao("Operação realizada com sucesso!");
		return getPaginaComprovanteCadastro();
	}
	
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Nome: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Código INEP: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty( obj.getModalidade() ) ) {
			addMensagemErro("Modalidade: campo obrigatório não informado");
		}
	}
	
	public void changeEstadoEndereco(){
		municipiosEndereco = new ArrayList<Municipio>();
		if( ValidatorUtil.isNotEmpty( obj.getEndereco().getEstado() ) ) {
			try {
				municipiosEndereco = (List<Municipio>) municipioService.getByEstado( obj.getEndereco().getEstado() );
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
	
	public String remover(Instituicao instituicao) {
		instituicao = service.getByPrimaryKey(Instituicao.class, instituicao.getId());
		if( ValidatorUtil.isEmpty(instituicao) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			instituicao = service.executeRemocao(instituicao);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
			return null;
		}
		
		lista.remove(instituicao);
		resetObj();
		addMensagemInformacao("Operação realizada com sucesso!");
		return getPaginaListagem();
	}
	

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma Instituição cadastrada com esse Código.";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "O registro não pode ser removido pois existe uma associação com outros registros utilizados no sistema.";
		}
		return e.getMessage();
	}

	
	public Collection<Instituicao> getAll() {
		return SIAECache.getInstituicoes();
	}

	public Instituicao getInstituicaoSelecionada() {
		return instituicaoVisualizacao;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoVisualizacao = instituicaoSelecionada;
	}
	public Collection<Municipio> getMunicipiosEndereco() {
		return municipiosEndereco;
	}

	public void setMunicipiosEndereco(Collection<Municipio> municipiosEndereco) {
		this.municipiosEndereco = municipiosEndereco;
	}

	public Instituicao getInstituicaoRemocao() {
		return instituicaoRemocao;
	}

	public void setInstituicaoRemocao(Instituicao instituicaoRemocao) {
		this.instituicaoRemocao = instituicaoRemocao;
	}

}
