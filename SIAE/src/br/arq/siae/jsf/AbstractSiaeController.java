package br.arq.siae.jsf;

import java.util.List;

import javax.faces.model.DataModel;

import br.arq.dao.Persistent;
import br.arq.jsf.AbstractController;


public class AbstractSiaeController<T extends Persistent> extends AbstractController{
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
	
	public String getPaginaCadastro() {
		return "/views/restrito/" + obj.getClass().getSimpleName().toLowerCase() + "/cadastro.jsf";
	}
	
	public String iniciarListagem() {
		return getPaginaListagem();
	}
	
	public String getPaginaListagem() {
		return "/views/restrito/" + obj.getClass().getSimpleName().toLowerCase() + "/listagem.jsf";
	}
	
	public String getPaginaComprovanteCadastro() {
		return "/views/restrito/" + obj.getClass().getSimpleName().toLowerCase() + "/comprovante_cadastro.jsf";
	}
	
}
