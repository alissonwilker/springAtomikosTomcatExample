package org.fabriki.excecao;

import javax.persistence.PersistenceException;

/**
 * Classe que representa uma exceção de Entidade que está em uso. 
 *
 * @see org.fabriki.excecao.RuntimeExcecao
 */
public class EntidadeEmUsoExcecao extends RuntimeExcecao {
    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoExcecao(PersistenceException pex) {
        super(pex);
    }

}
