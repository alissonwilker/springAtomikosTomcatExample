package org.fabriki.model.persistence.dao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;

import org.fabriki.model.persistence.FabrikiEntityManagerFactory;

/**
 * Classe abstrata que implementa comportamento padrão dos DAO's do Fabriki.
 * 
 * @see org.fabriki.model.persistence.dao.AbstractDao
 */
@RequestScoped
public abstract class AbstractFabrikiDao<E, P extends Serializable> extends AbstractDao<E, P> {
    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
//        entityManager = FabrikiEntityManagerFactory.createEntityManager();
    }
    
    @PreDestroy
    public void finalize() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
