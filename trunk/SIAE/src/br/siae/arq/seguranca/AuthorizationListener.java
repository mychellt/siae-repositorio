package br.siae.arq.seguranca;

import java.io.IOException;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

public class AuthorizationListener implements PhaseListener{
	private static final long serialVersionUID = 1L;
	public static final String PAGINA_LOGIN = "/views/publico/login.jsf";

	@Override
	public void afterPhase(PhaseEvent event) { }

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		ExternalContext ec = facesContext.getExternalContext();
		String requestContextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); 
		Boolean restrito = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI().indexOf(requestContextPath+ "/views/restrito/") == 0;
		Boolean login = ((HttpServletRequest) facesContext.getExternalContext().getRequest()).getRequestURI().indexOf(PAGINA_LOGIN) == 0;
		Boolean logado = Boolean.TRUE;
		if (restrito) {  
            logado = facesContext.getExternalContext().getSessionMap().get("usuario") != null;
        } 
		
		if( !login && !logado ) {
			 try {
				ec.redirect(requestContextPath + PAGINA_LOGIN);
			} catch (IOException e) {
				 throw new FacesException(e);
			}
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
