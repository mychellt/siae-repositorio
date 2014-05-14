package br.arq.jsf;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.arq.utils.RequestUtils;
import br.arq.utils.ValidatorUtil;


public class AbstractController {	
	
	@ManagedProperty(value = "#{facesContext}")
	private FacesContext context;
	
	private static ApplicationContext appContext;	
	
	protected String confirmButton = "Cadastrar";
	
	
	public String redirect(String caminho) {
		return caminho + "faces-redirect=true";
	}
	
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public void addMensagemErro(String mensagem) {
		addMessage(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	public void addMensagemWarning(String mensagem) {
		addMessage(FacesMessage.SEVERITY_WARN, mensagem);
	}
	public void addMensagemInformacao(String mensagem) {
		addMessage(FacesMessage.SEVERITY_INFO, mensagem);
	}
	public void addMensagemErroFatal(String mensagem) {
		addMessage(FacesMessage.SEVERITY_FATAL, mensagem);
	}
	
	private void addMessage(FacesMessage.Severity severidade, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severidade, mensagem, null);
		getContext().addMessage(null, facesMessage);
	}
  
	public boolean isContemErros() {
		return ValidatorUtil.isNotEmpty( getContext().getMessageList() );
	}

	private ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public HttpServletResponse getCurrentResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}
	public HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}
	public void setConfirmButton(String confirmButton) {
		this.confirmButton = confirmButton;
	}

	public String getConfirmButton() {
		return confirmButton;
	}
	
	public String forward(String url) {
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		UIViewRoot view = app.getViewHandler().createView(context, url);
		context.setViewRoot(view);
		context.renderResponse();
		return null;
	}
	
	public String cancelar() {
		return null;
	}
	
	public synchronized ApplicationContext getApplicationContext() {

		if (appContext == null) {
			appContext = new  ClassPathXmlApplicationContext("/applicationContext.xml");
		}
		return appContext;
	}

	/**
	 * Retorna um BEan do Spring com responsabilidade da camada de serviço
	 * 
	 * @param name
	 * @return
	 */
	public Object getService(String name) {
		return getApplicationContext().getBean(name);
	}	
	
	/** 
	 * Pega um parâmetro em request e converte para Integer. Caso
	 * o parâmetro não exista, retorna null.
	 */
	public Integer getParameterInt(String param) {
		return RequestUtils.getParameterInt(param, 0, getCurrentRequest());
	}
	
	/**
	 * Possibilita o acesso ao HttpSession.
	 */
	public HttpSession getCurrentSession() {
		return getCurrentRequest().getSession(true);
	}

	public String home() {
		return "/views/restrito/principal.jsf";
	}
	
}