package br.siae.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.seguranca.Usuario;
import br.arq.service.AbstractService;
import br.arq.utils.CalendarUtils;
import br.arq.utils.ValidatorUtil;
import br.siae.dao.MatriculaDAO;
import br.siae.dao.RequerimentoMatriculaDAO;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.ItemRequerimentoMatricula;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.dominio.academico.Turma;

@Service
@Transactional
public class MatriculaAlunoService extends AbstractService{
	
	@Resource(name="matriculaDAO")
	private MatriculaDAO dao;
	
	@Resource(name="requerimentoMatriculaDAO")
	private RequerimentoMatriculaDAO requerimentoDAO;
	
	public RequerimentoMatricula executeRealizacaoMatricula(List<Turma> turmas, Aluno aluno ) throws NegocioException {
		RequerimentoMatricula requerimentoMatricula = new RequerimentoMatricula();
		
		validar(turmas, aluno);
		
		if(ValidatorUtil.isEmpty(aluno.getRequerimentoMatricula())) {
			for(Turma turma: turmas){
				ItemRequerimentoMatricula item = new ItemRequerimentoMatricula();
				item.setAno(CalendarUtils.getAnoAtual());
				item.setData(new Date());
				item.setRequerimentoMatricula(requerimentoMatricula);
				long numeroVagas = turma.getNumeroVagas();
				turma.setNumeroVagas(numeroVagas--);
				item.setTurma(turma);
				requerimentoMatricula.setItens(new ArrayList<ItemRequerimentoMatricula>());
				requerimentoMatricula.getItens().add(item);
			}
			NumberFormat formatter = new DecimalFormat("00000"); 
			String numeroMatricula = CalendarUtils.getAnoAtual() + formatter.format(dao.nextNumeroMatricula());
			requerimentoMatricula.setNumeroMatricula(Long.parseLong(numeroMatricula));
			requerimentoMatricula.setDataCadastro(new Date());
			requerimentoMatricula.setAluno(aluno);
			Usuario usuario = new Usuario();
			usuario.setId(1);
			requerimentoMatricula.setResponsavelCadastro(usuario);
			dao.create(requerimentoMatricula);
			return requerimentoMatricula;
		}
		return requerimentoMatricula;
	}
	
	public void validar(List<Turma> turmas, Aluno aluno) throws NegocioException{
		for(Turma turma : turmas) {
			//Verificar se o aluno já está matricula em alguma das turmas.
			if(ValidatorUtil.isNotEmpty(dao.findByAlunoTurma(aluno, turma))){
				throw new NegocioException("A aluno já está matriculado na turma: " + turma.getNome());
			}
			//Verificar se ainda existe vagas para a turma.
			if(turma.getNumeroVagas() == 0 ){
				throw new NegocioException("Não há mais vagas disponíveis para a turma: " + turma.getNome());
			}
			
		}
	}
	
	public RequerimentoMatricula getRequerimentoAluno(Aluno aluno) throws NegocioException {
		return requerimentoDAO.findByAluno(aluno);
	}
}
