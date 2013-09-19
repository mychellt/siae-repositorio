package br.siae.matricula.jsf;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.siae.arq.jsf.AbstractSiaeController;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Serie;
import br.siae.dominio.academico.Turma;
import br.siae.dominio.comum.Turno;
import br.siae.jsf.TurmaDataModel;
import br.siae.service.TurmaService;

@Controller
@Scope("session")
public class ConsultadorTurmaMBean extends AbstractSiaeController<Turma>{
	/** Formulário para matricula do aluno. */
	public static final String FORM_MATRICULA = "/views/restrito/matricula/form_matricula.jsf";
	
	private boolean porNome;
	private boolean porAno;
	private boolean porSerie;
	private boolean porTurno;
	private Turma[] arrayTurma;
	private TurmaDataModel dataModel;

	
	
	@Resource(name="turmaService")
	private TurmaService service;
	
	public ConsultadorTurmaMBean() {
		obj = new Turma();
		obj.setSerie( new Serie() );
		obj.setTurno( new Turno() );
	}


	public String consultar() {
		validar();
		
		if( isContemErros() ) {
			return null;
		}
		lista = service.getByCriterios(obj);
		dataModel = new TurmaDataModel();
		dataModel.setWrappedData(lista);
		return FORM_MATRICULA;
	}
	

	private void validar()  {
		if( !isPorNome() &&  !isPorSerie() && !isPorTurno() ) {
			addMensagemErro("Seleciona pelo menos um critério de busca.");
		}
		if(isPorNome() && ValidatorUtil.isEmpty(obj.getNome())){
			addMensagemErro("Nome: campo obrigatório não informado.");
		}
		if(isPorTurno() && ValidatorUtil.isEmpty(obj.getTurno())){
			addMensagemErro("Turno: campo obrigatório não informado.");
		}
		if(isPorSerie() && ValidatorUtil.isEmpty(obj.getSerie())) {
			addMensagemErro("Série: campo obrigatório não informado.");
		}
	}


	public boolean isPorNome() {
		return porNome;
	}


	public boolean isPorAno() {
		return porAno;
	}


	public boolean isPorSerie() {
		return porSerie;
	}


	public boolean isPorTurno() {
		return porTurno;
	}


	public void setPorNome(boolean porNome) {
		this.porNome = porNome;
	}


	public void setPorAno(boolean porAno) {
		this.porAno = porAno;
	}


	public void setPorSerie(boolean porSerie) {
		this.porSerie = porSerie;
	}


	public void setPorTurno(boolean porTurno) {
		this.porTurno = porTurno;
	}

	public void setService(TurmaService service) {
		this.service = service;
	}


	public TurmaDataModel getDataModel() {
		return dataModel;
	}


	public void setDataModel(TurmaDataModel dataModel) {
		this.dataModel = dataModel;
	}


	public Turma[] getArrayTurma() {
		return arrayTurma;
	}


	public void setArrayTurma(Turma[] arrayTurma) {
		this.arrayTurma = arrayTurma;
	}
	
	public void porNome(Boolean value) {
		setPorNome(value);
	}
	public void porSerie(Boolean value) {
		setPorSerie(value);
	}
	public void porTurno(Boolean value) {
		setPorTurno(value);
	}
	
}
