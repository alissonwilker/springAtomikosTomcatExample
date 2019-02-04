package org.fabriki.model.persistence.dao;

import java.io.Serializable;

/**
 * Classe abstrata que implementa comportamento padrão dos DAO's do Fabriki.
 * 
 * @see org.fabriki.model.persistence.dao.AbstractDao
 */
public abstract class AbstractFabrikiDao<E, P extends Serializable> extends AbstractDao<E, P> {

//    @Override
//	@PreDestroy
//    public void finalize() {
//        if (entityManager != null) {
//            entityManager.close();
//        }
//    }
}
