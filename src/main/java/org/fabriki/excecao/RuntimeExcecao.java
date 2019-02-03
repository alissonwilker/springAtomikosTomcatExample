package org.fabriki.excecao;

/**
 * Classe abstrata que representa uma exceção genérica do sistema.
 *
 * @see java.lang.Exception
 */
public class RuntimeExcecao extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public RuntimeExcecao() {
    }

    public RuntimeExcecao(String mensagem) {
        super(mensagem);
    }

    public RuntimeExcecao(Throwable causaRaiz) {
        super(causaRaiz);
    }

}
