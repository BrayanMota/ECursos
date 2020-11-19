package br.unitins.ecursos.application;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Session {
private static Session session = null;
	
	private Session() {}
	
	public static Session getInstance() {
		if (session == null)
			session = new Session();
		return session;
	}
	
	private ExternalContext getExternalContext() {
		if (FacesContext.getCurrentInstance().getExternalContext() == null) {
			throw new RuntimeException("Este recurso é para ser utilizado apenas em aplicações WEB.");
		}
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	
	public Object getAttribute(String key) {
		return getExternalContext().getSessionMap().get(key);
	}
	
	public void setAttribute(String key, Object value) {
		getExternalContext().getSessionMap().put(key, value);
	}
	
	public void invalidateSession() {
		getExternalContext().invalidateSession();
	}

}
