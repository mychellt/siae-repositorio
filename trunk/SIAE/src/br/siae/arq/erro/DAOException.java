
package br.siae.arq.erro;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
        super(msg);
        printStackTrace();
    }
    
    public DAOException(Exception e) {
        super(e);
    }
    
    public DAOException(String mensagem, Exception e) {
    	super(mensagem, e);
    }
}