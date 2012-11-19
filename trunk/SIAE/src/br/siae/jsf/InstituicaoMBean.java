package br.siae.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.erro.ArqException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.service.MunicipioService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Nivel;
import br.siae.dominio.comum.Instituicao;
import br.siae.service.InstituicaoService;

@Controller
@Scope("session")
public class InstituicaoMBean extends AbstractSiaeController<Instituicao> implements ArqException{
	
	@Resource(name="municipioService")
	private MunicipioService municipioService;
	
	@Resource(name="instituicaoService")
	private InstituicaoService service;
	
	private Instituicao instituicaoSelecionada;
	
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
		instituicaoSelecionada = new Instituicao();
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
		return getPaginaComprovante();
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

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma Instituição cadastrada com esse Código.";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}

	public Collection<Municipio> getMunicipiosEndereco() {
		return municipiosEndereco;
	}

	public void setMunicipiosEndereco(Collection<Municipio> municipiosEndereco) {
		this.municipiosEndereco = municipiosEndereco;
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

	public Instituicao getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}

}
