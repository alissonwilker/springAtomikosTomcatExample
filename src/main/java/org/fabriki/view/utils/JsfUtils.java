package org.fabriki.view.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe com métodos utilitários para facilitar acesso a diversos recursos oferecidos pelo JSF,
 * como FacesContext, Resource Bundle...
 *
 */
public class JsfUtils {
    public enum Pagina {
        index, envios, turma, exercicioTurma, notaAulaTurma, lembreteTurma, estudos;
    }

    public static String getCookieValue(String cookieKey) {
        Cookie cookie = ((Cookie) getExternalContext().getRequestCookieMap().get(cookieKey));
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }
    
    public static void addResponseCookie(String cookieKey, String cookieValue) {
        getExternalContext().addResponseCookie(cookieKey, cookieValue, null);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static Application getApplication() {
        return getFacesContext().getApplication();
    }

    public static UIViewRoot getViewRoot() {
        return getFacesContext().getViewRoot();
    }

    public static void setLocale(Locale locale) {
        getViewRoot().setLocale(locale);
    }

    public static Object getUiParam(String nomeUiParam) {
        return ((FaceletContext) getFacesContext().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY))
            .getAttribute(nomeUiParam);
    }

    public static ResourceBundle getResourceBundle(String key) {
        return getApplication().getResourceBundle(getFacesContext(), key);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }
    
    public static String getRequestParam(String param) {
        return getExternalContext().getRequestParameterMap().get(param);
    }

    public static ExternalContext getExternalContext() {
        return JsfUtils.getFacesContext().getExternalContext();
    }

    public static String getRedirecionamentoComMensagens(Pagina pagina) {
        return getRedirecionamentoComMensagens(pagina, false);
    }

    public static String getRedirecionamentoComMensagens(Pagina pagina, boolean mostrarSubmenuTurma) {
        getExternalContext().getFlash().setKeepMessages(true);
        return "/" + pagina.name() + "?faces-redirect=true&amp;" + (mostrarSubmenuTurma ? "turma=true" : "");
    }

    public static void redirecionar(Pagina pagina) throws IOException {
        getExternalContext().redirect(getExternalContext().getRequestContextPath() + "/" + pagina.name() + ".xhtml");
    }

}
