package br.siae.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.erros.ArqException;
import br.arq.erros.NegocioException;
import br.arq.siae.jsf.AbstractSiaeController;
import br.arq.utils.DAOUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.FormacaoAcademica;
import br.siae.dominio.academico.NivelFormacao;
import br.siae.service.FormacaoAcademicaService;


@Controller
@Scope("session")
public class FormacaoAcademicaMBean extends AbstractSiaeController<FormacaoAcademica> implements ArqException {

	@Resource(name="formacaoAcademicaService")
	private FormacaoAcademicaService service;
	
	private FormacaoAcademica formacao;
	
	public FormacaoAcademicaMBean() {
		resetObj();
	}
	
	private void resetObj() {
		obj = new FormacaoAcademica();
		obj.setNivel( new NivelFormacao() );
	}

	public String iniciarCadastro() {
		resetObj();
		try {
			lista = (List<FormacaoAcademica>) service.getAll( FormacaoAcademica.class );
		} catch (NegocioException e) {
			addMensagemErro(processaException(e));
		}
		setConfirmButton("Cadastrar");
		return getPaginaCadastro();
	}
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		obj = service.getByPrimaryKey(FormacaoAcademica.class, obj.getId());
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String remover(FormacaoAcademica formacao) {
		formacao = service.getByPrimaryKey(FormacaoAcademica.class, formacao.getId() );
		if( ValidatorUtil.isEmpty(formacao) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			formacao = service.executeRemocao(formacao);
		}
		catch(Exception e) {
			addMensagemErro( processaException(e) );
			return null;
		}		
		lista.remove(formacao);
		resetObj();
		addMensagemInformacao("Operação realizada com sucesso!");
		return getPaginaCadastro();
	}
	public String cadastrar() {
		validar();
		
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		try {
			obj.setNivel( service.getByPrimaryKey(NivelFormacao.class, obj.getNivel().getId() ) );
			
			if( ValidatorUtil.isNotEmpty(obj)) {
				FormacaoAcademica ObjRemocao = lista.get( lista.indexOf(obj) );
				obj = service.executeCadastro(obj);
				lista.remove(ObjRemocao);
				lista.add(obj);
			}
			else {
				obj = service.executeCadastro(obj);
				lista.add(obj);
			}
			
			service.executeCadastro(obj);
		} catch (Exception e) {
			addMensagemErro(processaException(e));
			return getPaginaCadastro();
		}
		resetObj();
		setConfirmButton("Cadastrar");
		addMensagemInformacao("Operação efetuada com sucesso!");
		return getPaginaCadastro();
	}
	
	private void validar() {
		if( ValidatorUtil.isEmpty(obj.getDenominacao()) ) {
			addMensagemErro("Curso: campo obrigatório não informado");
		}
		if( ValidatorUtil.isEmpty(obj.getNivel()) ) {
			addMensagemErro("Nível: campo obrigatório não informado");
		}
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma disciplina cadastrada com esse informações";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "O registro não pode ser removido pois existe uma associação com outros registros utilizados no sistema.";
		}
		return e.getMessage();
	}

	public FormacaoAcademica getFormacao() {
		return formacao;
	}

	public void setFormacao(FormacaoAcademica formacao) {
		this.formacao = formacao;
	}

}
