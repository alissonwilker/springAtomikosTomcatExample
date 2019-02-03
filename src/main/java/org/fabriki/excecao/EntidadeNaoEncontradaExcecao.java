package org.fabriki.excecao;

/**
 * Classe que representa uma exceção de Entidade não encontrada. Normalmente
 * lançada quando tenta-se realizar uma operação sobre uma Entidade que não
 * existe.
 *
 * @see org.fabriki.excecao.RuntimeExcecao
 */
public class EntidadeNaoEncontradaExcecao extends RuntimeExcecao {
    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaExcecao(IllegalArgumentException iaex) {
        super(iaex);
    }

    public EntidadeNaoEncontradaExcecao() {
        super();
    }

}
