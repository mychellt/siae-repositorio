package br.siae.arq.dominio;

/**
 * Interface implementada por todos os objetos persistentes.
 * <br/>
 * @author Mychell Teixeira
 *
 */
public interface Persistable {
	public long getId();
	public void setId( long id );
}
