package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.dominio.academico.Serie;

@Service
@Transactional
public class SerieService extends AbstractService{
	

	public Serie executeCadastro(Serie serie) throws NegocioException {
		return serie;
	}
		
	public Serie executeRemocao(Serie serie) throws NegocioException {
		return (Serie) remover(serie);
	}
}
