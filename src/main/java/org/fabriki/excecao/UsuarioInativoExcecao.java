package org.fabriki.excecao;

public class UsuarioInativoExcecao extends AbstractExcecao {
    private static final long serialVersionUID = 1L;
    
    public UsuarioInativoExcecao(String loginUsuario) {
        super("O usuário '" + loginUsuario + "' está inativo.");
    }
}
