package org.fabriki.excecao;

import javax.persistence.PersistenceException;

/**
 * Classe que representa uma exceção de Entidade que já existe. Normalmente
 * lançada quando uma Entidade que já existe está tentando ser cadastrada
 * novamente.
 *
 * @see org.fabriki.excecao.RuntimeExcecao
 */
public class EntidadeJaExisteExcecao extends RuntimeExcecao {
    private static final long serialVersionUID = 1L;

    public EntidadeJaExisteExcecao(PersistenceException pex) {
        super(pex);
    }

}
