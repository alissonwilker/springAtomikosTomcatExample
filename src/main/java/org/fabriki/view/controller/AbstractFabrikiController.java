package org.fabriki.view.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.fabriki.utils.IFabrikiConstantes;
import org.fabriki.view.utils.JsfUtils;

public abstract class AbstractFabrikiController<D, P extends Serializable> extends AbstractController<D, P> {
    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
        recuperarCredenciaisUsuarioLogado();

        super.init();
    }

    protected void recuperarCredenciaisUsuarioLogado() {
        setAccessToken();
    }

    protected void setAccessToken() {
        // nada a fazer nessa classe. Subclasses podem implementar um comportamento.
    }

    protected boolean verificarSeUsuarioEhAdmin(String userLogin) {
        return (boolean) JsfUtils.getUiParam(IFabrikiConstantes.USUARIO_LOGADO_EH_ADMIN);
    }

}
