package br.siae.jsf;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.TipoPessoa;
import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class AlunoMBean extends AbstractSiaeController<Aluno>{

	@Resource(name="pessoaMBean")
	private PessoaMBean pessoaMBean;
	
	@Resource(name="alunoService")
	private AlunoService alunoService;
	
	public AlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
	}
	
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastro de Aluno");
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(true);
		return getPaginaCadastro();
	}
	
	public String iniciarListagem() {
		
		return getPaginaListagem();
	}
	
	
	public String preAlterar() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		pessoaMBean.setObj( obj.getPessoa() );
		pessoaMBean.setControlador(this);
		pessoaMBean.setExibirInfoCpf(false);
		setConfirmButton("Alterar");
		return getPaginaCadastro();
	}
	
	public String cadastrar() {
		pessoaMBean.validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		obj.setPessoa( pessoaMBean.getObj() );
		obj.getPessoa().setTipo( new TipoPessoa( TipoPessoa.PESSOA_FISICA ) );
		
		try {
			obj = alunoService.executeCadastro( obj );
		} catch (NegocioException e) {
			e.printStackTrace();
			addMensagemErro( e.getMessage() );
		} catch (DAOException e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro ao tentar inserir o aluno na base de dados. Por favor entre em contato com o administrador do sistema.");
		}
		
		addMensagemInformacao("Cadastro do aluno efetuado com sucesso!");
		return PessoaMBean.COMPROVANTE_CADASTRO;
	}
	public String remover() {
		if( ValidatorUtil.isEmpty(obj) ) {
			addMensagemErro("O elemento selecionando não se encontra na base de dados.");
			resetObj();
			return null;
		}
		try {
			obj = alunoService.executeRemocao(obj);
		} catch (NegocioException e) {
			e.printStackTrace();
			addMensagemErro( e.getMessage() );
		}
		catch (DAOException e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro ao tentar inserir o aluno na base de dados. Por favor entre em contato com o administrador do sistema.");
		}
		return getPaginaListagem();
	}
}
