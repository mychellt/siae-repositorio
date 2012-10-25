package br.siae.jsf;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.DAOException;
import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractCrudController;
import br.siae.arq.jsf.PessoaMBean;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.service.AlunoService;

@Controller
@Scope("session")
public class AlunoMBean extends AbstractCrudController<Aluno>{
	
	public static final String COMPROVANTE_CADASTRO = "";
	
	@Autowired
	private PessoaMBean pessoaMBean;
	
	@Resource(name="alunoService")
	private AlunoService alunoService;
	
	public AlunoMBean() {
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
	}
	
	@Override
	public String iniciarCadastro() {
		resetObj();
		pessoaMBean.resetObj();
		obj.setPessoa( pessoaMBean.getObj() );
		pessoaMBean.setDescricaoCadastro("Cadastro de Aluno");
		pessoaMBean.setControlador(this);
		return super.iniciarCadastro();
	}
	
	public String cadastrar() {
		validate();
		if( isContemErros() ) {
			return getPaginaCadastro();
		}
		
		obj.setPessoa( pessoaMBean.getObj() );
		
		try {
			obj = alunoService.executarCadastro( obj );
		} catch (NegocioException e) {
			e.printStackTrace();
			addMensagemErro( e.getMessage() );
		} catch (DAOException e) {
			e.printStackTrace();
			addMensagemErro("Ocorreu um erro ao tentar inserir o aluno na base de dados. Por favor entre em contato com o administrador do sistema.");
		}
		
		return COMPROVANTE_CADASTRO;
	}
	
	private void validate( ) {
		if( ValidatorUtil.isEmpty( obj.getPessoa().getNome() ) ) {
			addMensagemErro("Nome: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getPessoa().getDataNascimento() ) ) {
			addMensagemErro("Date de Nascimento: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getPessoa().getCpf() ) ) {
			addMensagemErro("CPF: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getPessoa().getNomeMae() ) ) {
			addMensagemErro("Nome da Mãe: campo obrigatório não informado.");
		}
	}
}
