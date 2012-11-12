package br.siae.arq.erro;

import java.util.Collection;

public class NegocioException extends Exception {
	private static final long serialVersionUID = 1L;
	private Collection<?> objetos;

	private Object objeto;

	public NegocioException(Exception e) {
		super(e);
	}

	public NegocioException(String msg) {
		super(msg);
	}

	public NegocioException() {
		super("");
	}
	
	public Collection<?> getObjetos() {
		return objetos;
	}

	public void setObjetos(Collection<?> objetos) {
		this.objetos = objetos;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
}