package br.siae.matricula.jsf;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.matricula.service.ConsultaAlunoService;

@Controller
@Scope("session")
public class ConsultadorAlunoMBean extends AbstractSiaeController<Aluno>{
	
	@Resource(name="consultaAlunoService")
	private ConsultaAlunoService service;
	
	private Aluno selecionado;
	
	public ConsultadorAlunoMBean() {
		selecionado = new Aluno();
		obj = new Aluno();
	}
	
	private boolean porNome;
	private boolean porCPF;
	private boolean porMatricula;
	
	
	
	private void validar()  {
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
	
	public Collection<Aluno> buscar( ) throws NegocioException {
		validar();
		if(isContemErros()) {
			return null;
		}
		return service.busca(obj);
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
	
	
}
