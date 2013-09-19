package br.siae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.utils.ValidatorUtil;
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
