package br.siae.arq.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.siae.arq.erro.NegocioException;
import br.siae.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Cargo;

@Service
@Transactional
public class CargoService extends AbstractService {
	public Cargo executeCadastro( Cargo cargo ) throws NegocioException {
		if( ValidatorUtil.isEmpty(cargo) ) {
			cadastrar(cargo);
		}
		else {
			alterar(cargo);
		}
		return cargo;
	}
}
