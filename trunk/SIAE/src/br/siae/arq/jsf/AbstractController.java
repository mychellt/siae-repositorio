package br.siae.arq.jsf;

import javax.el.PropertyNotWritableException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.siae.arq.dominio.Usuario;
import br.siae.arq.utils.ReflectionUtils;
import br.siae.arq.utils.RequestUtils;
import br.siae.arq.utils.ValidatorUtil;

public class AbstractController {		
	private static ApplicationContext appContext;	
	
	protected String confirmButton = "Cadastrar";
	
	
	public String redirect(String caminho) {
		return caminho + "faces-redirect=true";
	}
	
	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
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
	
	public String cancelar() {
		resetBean();
		return null;
	}
	
	public synchronized ApplicationContext getApplicationContext() {

		if (appContext == null) {
			appContext = new  ClassPathXmlApplicationContext("/applicationContext.xml");
		}
		return appContext;
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
	
	
	public void resetBean() {
		/** removendo operação ativa */
		getCurrentSession().removeAttribute("operacaoAtiva");
		
		Component anotComponent = getClass().getAnnotation(Component.class);
		if (anotComponent != null) {
			String mbeanName = anotComponent.value();
			if ( ValidatorUtil.isEmpty(mbeanName)) {
				mbeanName = StringUtils.uncapitalize(getClass().getSimpleName());
			}
			resetBean(mbeanName);
		}
	}
	
	public static void resetBean(String name) {
		if (!ValidatorUtil.isEmpty(name)) {
			Object mbean =  getMBean(name);
			if(mbean != null) {
				try {
					FacesContext fc = FacesContext.getCurrentInstance();
					fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(), "#{" + name + "}", getMBean(name).getClass()).setValue(fc.getELContext(), null);
				} catch(PropertyNotWritableException e) {
					Object clean = ReflectionUtils.instantiateClass(mbean.getClass());
					ReflectionUtils.copyProperties(clean, mbean);
				}
			}
		}
	}
	
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		return usuario;
	}
	
	public boolean isUsuarioLogado() {
		return ValidatorUtil.isNotEmpty(getUsuarioLogado()); 
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getMBean(String mbeanName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		return (T) fc.getELContext().getELResolver().getValue(fc.getELContext(), null, mbeanName);
	}
	
	public String home() {
		return "/views/restrito/principal.jsf";
	}
}