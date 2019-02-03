package org.fabriki.excecao;

/**
 * Classe abstrata que representa uma exceção genérica do sistema.
 *
 * @see java.lang.Exception
 */
public abstract class AbstractExcecao extends Exception {
    private static final long serialVersionUID = 1L;

    public AbstractExcecao() {
    }
    
    public AbstractExcecao(String mensagem) {
        super(mensagem);
    }

    public AbstractExcecao(Throwable causaRaiz) {
        super(causaRaiz);
    }

}
