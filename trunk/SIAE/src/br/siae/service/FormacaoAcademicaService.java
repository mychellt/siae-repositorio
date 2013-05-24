package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.service.AbstractService;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.academico.FormacaoAcademica;

@Service
@Transactional
public class FormacaoAcademicaService extends AbstractService{
	public FormacaoAcademica executeCadastro( FormacaoAcademica formacao ) throws NegocioException {
		if( ValidatorUtil.isEmpty(formacao) ) {
			cadastrar(formacao);
		}
		else {
			alterar(formacao);
		}
		return formacao;
	}
	public FormacaoAcademica executeRemocao( FormacaoAcademica formacao ) throws NegocioException {
		formacao = (FormacaoAcademica) remover(formacao);
		return formacao;
	}
}
