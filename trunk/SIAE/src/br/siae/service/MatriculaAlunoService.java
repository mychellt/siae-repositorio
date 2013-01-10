package br.siae.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.Aluno;
import br.siae.dominio.academico.ItemRequerimentoMatricula;
import br.siae.dominio.academico.RequerimentoMatricula;
import br.siae.dominio.academico.Turma;

@Service
@Transactional
public class MatriculaAlunoService extends AbstractService{
	public RequerimentoMatricula executeRealizacaoMatricula( Aluno aluno, Turma turma ) throws NegocioException {
		RequerimentoMatricula requerimentoMatricula = new RequerimentoMatricula();
		if( ValidatorUtil.isEmpty( aluno.getRequerimentoMatricula() ) ) {
			requerimentoMatricula = aluno.getRequerimentoMatricula();
		}
		else {
			requerimentoMatricula.setNumeroMatricula(1);
			aluno.setRequerimentoMatricula(requerimentoMatricula);
		}
		Calendar calendar = GregorianCalendar.getInstance();  
		ItemRequerimentoMatricula item = new ItemRequerimentoMatricula();
		item.setId( calendar.get(Calendar.YEAR) );
		item.setRequerimentoMatricula( requerimentoMatricula );
		item.setTurma(turma);
		item.setData( new Date() );
		cadastrar(requerimentoMatricula);
		return requerimentoMatricula;
	}
}
