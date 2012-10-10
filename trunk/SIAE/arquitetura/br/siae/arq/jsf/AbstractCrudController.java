package br.siae.arq.jsf;

import java.util.List;

import javax.faces.model.DataModel;

import br.siae.arq.dominio.Persistable;


public class AbstractCrudController<T extends Persistable> extends AbstractController {
	protected T obj;
	protected List<T> lista;
	protected DataModel<T> colecao;
	
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public List<T> getLista() {
		return lista;
	}
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	public DataModel<T> getColecao() {
		return colecao;
	}
	public void setColecao(DataModel<T> colecao) {
		this.colecao = colecao;
	}
	
	public String iniciarCadastro() {
		return "/views/restrito/" + obj.getClass().getSimpleName().toLowerCase() + "/cadastro";
	}
	
	public String iniciarListagem() {
		return "/views/restrito/" + obj.getClass().getSimpleName().toLowerCase() + "/listagem";
	}
}
