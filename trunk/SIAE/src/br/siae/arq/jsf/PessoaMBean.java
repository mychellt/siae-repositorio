package br.siae.arq.jsf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.dominio.CertidaoNascimento;
import br.siae.arq.dominio.CertificadoMilitar;
import br.siae.arq.dominio.Endereco;
import br.siae.arq.dominio.Identidade;
import br.siae.arq.dominio.Logradouro;
import br.siae.arq.dominio.Municipio;
import br.siae.arq.dominio.Naturalidade;
import br.siae.arq.dominio.Pessoa;
import br.siae.arq.dominio.TituloEleitor;
import br.siae.arq.erro.ArqException;
import br.siae.arq.service.MunicipioService;
import br.siae.arq.service.PessoaService;
import br.siae.arq.utils.DAOUtils;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.jsf.AlunoMBean;
import br.siae.jsf.FuncionarioMBean;
import br.siae.jsf.ProfessorMBean;

@Controller
@Scope("session")
public class PessoaMBean extends AbstractSiaeController<Pessoa> implements ArqException{
	/** Xhtml para o comprovante de cadastro da pessoa.*/
	public static final String COMPROVANTE_CADASTRO = "/views/restrito/pessoa/comprovante.jsf";
	
	/** Descrição da operação a ser realizada: cadastrar aluno, cadastrar professor ou cadastrar funcionário.*/
	private String descricaoCadastro = "Cadastrar Pessoa";
	
	/** Controlador manipulador.*/
	private AbstractController controlador;
	
	/** Indica que o Dialog para informar o cpf da pessoa deve ser exibido.*/
	private boolean exibirInfoCpf;
	
	/** Mensagem de validação para indicação do cpf.*/
	private String mensagemErroCpf;
	
	/** Cpf informado no início do cadastro. */
	private long cpf;
	
	/** Municípios para a naturalidade da pessoa.*/
	private Collection<Municipio> municipiosNaturalidade;
	
	/** Municípios para o endereço da pessoa. */
	private Collection<Municipio> municipiosEndereco;
	
	@Resource(name="pessoaService")
	private PessoaService pessoaService;
	
	@Resource(name="municipioService")
	private MunicipioService municipioService;
	
	public PessoaMBean() {
		resetObj();
	}

	public void resetObj() {
		obj = new Pessoa();
		obj.setEndereco( new Endereco() );
		obj.getEndereco().setLogradouro( new Logradouro() );
		obj.setIdentidade( new Identidade() );
		obj.setNaturalidade( new Naturalidade() );
		obj.setTituloEleitor( new TituloEleitor() );
		obj.setCertificadoMilitar( new CertificadoMilitar() );
		obj.setCertidaoNascimento( new CertidaoNascimento() );
	}

	public String getDescricaoCadastro() {
		return descricaoCadastro;
	}

	public void setDescricaoCadastro(String descricaoCadastro) {
		this.descricaoCadastro = descricaoCadastro;
	}
	
	public AbstractController getControlador() {
		return controlador;
	}

	public void setControlador(AbstractController controlador) {
		this.controlador = controlador;
	}
	
	public void validate( ) {
		if( ValidatorUtil.isEmpty( obj.getNome() ) ) {
			addMensagemErro("Nome: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getDataNascimento() ) ) {
			addMensagemErro("Date de Nascimento: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getNomeMae() ) ) {
			addMensagemErro("Nome da Mãe: campo obrigatório não informado.");
		}
		if( ValidatorUtil.isEmpty( obj.getSexo() ) ) {
			addMensagemErro("Sexo: campo obrigatório não informado.");
		}
	}

	public boolean isExibirInfoCpf() {
		return exibirInfoCpf;
	}

	public void setExibirInfoCpf(boolean exibirInfoCpf) {
		this.exibirInfoCpf = exibirInfoCpf;
	}

	public String getMensagemErroCpf() {
		return mensagemErroCpf;
	}

	public void setMensagemErroCpf(String mensagemErroCpf) {
		this.mensagemErroCpf = mensagemErroCpf;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	@Override
	public String processaException(Exception e) {
		e.printStackTrace();
		if( DAOUtils.isUniqueConstraintErro(e) ) {
			return "Já existe uma Pessoa cadastrada com esse cpf";
		}
		if( DAOUtils.isFKConstraintError(e) ) {
			return "Ocorreu um erro ao tentar remover o registro. Por favor entre em contato com o administrador do sistema.";
		}
		return e.getMessage();
	}

	
	public void changeEstadoNaturalidade(){
		municipiosNaturalidade = new ArrayList<Municipio>();
		if( ValidatorUtil.isNotEmpty( obj.getNaturalidade().getEstado() ) ) {
			try {
				municipiosNaturalidade = (List<Municipio>) municipioService.getByEstado( obj.getNaturalidade().getEstado() );
			} catch (Exception e) {
				e.getStackTrace();
			}
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

	public Collection<Municipio> getMunicipiosEndereco() {
		return municipiosEndereco;
	}

	public void setMunicipiosEndereco(Collection<Municipio> municipiosEndereco) {
		this.municipiosEndereco = municipiosEndereco;
	}

	public Collection<Municipio> getMunicipiosNaturalidade() {
		return municipiosNaturalidade;
	}

	public void setMunicipiosNaturalidade(Collection<Municipio> municipiosNaturalidade) {
		this.municipiosNaturalidade = municipiosNaturalidade;
	}
	
	public boolean isCadastroProfessor() {
		return controlador instanceof ProfessorMBean;
	}
	
	public boolean isCadastroAluno() {
		return controlador instanceof AlunoMBean;
	}
	
	public boolean isCadastroFuncionario() {
		return controlador instanceof FuncionarioMBean;
	}
	
	public boolean isAdministradorSistema() {
		return true;
	}
	
	public String getNomeObjCadastro() {
		if( isCadastroAluno() ) return "Aluno";
		if( isCadastroFuncionario() ) return "Funcionário";
		if( isCadastroProfessor() ) return "Professor";
		return "";
	}
}
