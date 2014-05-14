package br.siae.service;

import org.springframework.stereotype.Service;

import br.arq.erros.NegocioException;
import br.arq.service.AbstractService;
import br.arq.utils.ValidatorUtil;
import br.siae.dominio.rh.Cargo;

@Service
public class CargoService extends AbstractService{
	public Cargo executeCadastro( Cargo cargo ) throws NegocioException {
		if( ValidatorUtil.isEmpty(cargo) ) {
			cadastrar(cargo);
		}
		else {
			alterar(cargo);
		}
		return cargo;
	}
	public Cargo executeRemocao( Cargo cargo ) throws NegocioException {
		cargo = (Cargo) remover(cargo);
		return cargo;
	}
}
