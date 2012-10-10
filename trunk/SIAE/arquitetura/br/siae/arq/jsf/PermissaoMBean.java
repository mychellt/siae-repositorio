package br.siae.arq.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dao.GenericDAO;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.negocio.ProcessadorPermissao;
import br.siae.arq.seguranca.Permissao;
import br.siae.arq.service.ServiceFactory;
import br.siae.arq.utils.ValidatorUtil;


@Controller
@Scope("session")
public class PermissaoMBean extends AbstractCrudController<Permissao>{
	public static final String FORM_CADASTRO = "/views/restrito/permissao/cadastro";
	
	@Resource(name="processadorPermissao")
	private ProcessadorPermissao processador;
	
	public PermissaoMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new Permissao();
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		lista = (List<Permissao>) dao.findAll(Permissao.class);
		return super.iniciarCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty( obj.getDenominacao() ) ) {
			addMensagemErro("Denominação: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getDescricao() ) ) {
			addMensagemErro("Descrição: campo obrigatório não informado.");
		}
	}
	
	
	public String preAlterar() {
		GenericDAO dao = (GenericDAO) ServiceFactory.getBean("genericDAO");
		obj =  dao.findByPrimaryKey( Permissao.class, obj.getId() );
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		setConfirmButton("Alterar");
		return FORM_CADASTRO;
	}
	
	public String cadastrar() throws DAOException {
		validar();
		if( isContemErros() ) {
			return null;
		}
		try {
			if( ValidatorUtil.isNotEmpty(obj)) {
				lista.remove(obj);
				obj = processador.cadastrarPermissao(obj);
				addMensagemInformacao("Permissão alterada com sucesso!");
				lista.add(obj);
			}
			else {
				obj = processador.cadastrarPermissao(obj);
				lista.add(obj);
				addMensagemInformacao("Permissão cadastrada com sucesso!");
			}
			resetObj();
		} catch (NegocioException e) {
			addMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
		setConfirmButton("Cadastrar");
		return FORM_CADASTRO;
	}
}
