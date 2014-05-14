package br.siae.matricula.jsf;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.arq.dominio.CertidaoNascimento;
import br.arq.dominio.CertificadoMilitar;
import br.arq.dominio.Endereco;
import br.arq.dominio.Identidade;
import br.arq.dominio.Naturalidade;
import br.arq.dominio.Pessoa;
import br.arq.dominio.TituloEleitor;
import br.arq.erros.NegocioException;
import br.arq.siae.jsf.AbstractSiaeController;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.matricula.service.ConsultaAlunoService;

@Controller
@Scope("session")
public class ConsultadorAlunoMBean extends AbstractSiaeController<Aluno> {
	/** Página para seleção do aluno a ser matriculado. */
	public static final String SELECAO_ALUNO_MATRICULA = "/views/restrito/matricula/selecao_aluno_matricula.jsf";
	
	@Resource(name="consultaAlunoService")
	private ConsultaAlunoService service;
	
	private Aluno selecionado;
	
	public ConsultadorAlunoMBean() {
		selecionado = new Aluno();
		resetObj();
	}

	private void resetObj() {
		obj = new Aluno();
		obj.setPessoa( new  Pessoa() );
		obj.getPessoa().setCertidaoNascimento( new CertidaoNascimento() );
		obj.getPessoa().setCertificadoMilitar( new CertificadoMilitar() );
		obj.getPessoa().setIdentidade( new Identidade() );
		obj.getPessoa().setNaturalidade( new Naturalidade() );
		obj.getPessoa().setEndereco( new Endereco() );
		obj.getPessoa().setNaturalidade( new Naturalidade() );
		obj.getPessoa().setTituloEleitor( new TituloEleitor() );
		obj.setRequerimentoMatricula( new RequerimentoMatricula() );
	}
	
	private boolean porNome;
	private boolean porCPF;
	private boolean porMatricula;
	
	
	
	private void validar()  {
		if( !isPorCPF() &&  !isPorMatricula() && !isPorNome() ) {
			addMensagemErro("Seleciona pelo menos um critério de busca.");
		}
		if(isPorCPF() && ValidatorUtil.isEmpty(obj.getPessoa().getCpf())){
			addMensagemErro("CPF: campo obrigatório não informado.");
		}
		if(isPorNome() && ValidatorUtil.isEmpty(obj.getPessoa().getNome())){
			addMensagemErro("Nome: campo obrigatório não informado.");
		}
		if(isPorMatricula() && ValidatorUtil.isEmpty(obj.getRequerimentoMatricula().getNumeroMatricula())) {
			addMensagemErro("Matrícula: campo obrigatório não informado.");
		}
	}
	
	public String buscar( ) throws NegocioException {
		validar();
		if(isContemErros()) {
			return null;
		}
	    lista = (List<Aluno>) service.busca(obj);
	    resetObj();
	    return SELECAO_ALUNO_MATRICULA;
	}
	
	public boolean isPorNome() {
		return porNome;
	}
	public void setPorNome(boolean porNome) {
		this.porNome = porNome;
	}
	public boolean isPorCPF() {
		return porCPF;
	}
	public void setPorCPF(boolean porCPF) {
		this.porCPF = porCPF;
	}
	public boolean isPorMatricula() {
		return porMatricula;
	}
	public void setPorMatricula(boolean porMatricula) {
		this.porMatricula = porMatricula;
	}

	public Aluno getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Aluno selecionado) {
		this.selecionado = selecionado;
	}
	
	public void porNome(Boolean value){
		setPorNome(value);
	}
	public void porCPF(Boolean value) {
		setPorCPF(value);
	}
	public void porMatricula(Boolean value){
		setPorMatricula(value);
	}
	
}
