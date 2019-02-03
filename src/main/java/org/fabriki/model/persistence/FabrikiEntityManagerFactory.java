package org.fabriki.model.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Factory de EntityManager que eh instanciada e cria o EntityManager quando o contexto da aplicacao
 * web eh iniciado. Quando o contexto da aplicacao web eh finalizado, a Factory e seu EntityManager
 * sao fechados tambem. A Factory deve ser usada em toda a aplicacao para obter o EntityManager.
 *
 */
@WebListener
public class FabrikiEntityManagerFactory implements ServletContextListener {
    private static final String FABRIKI_PERSISTENCE_UNIT = "FabrikiPersistenceUnit";
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        emf = Persistence.createEntityManagerFactory(FABRIKI_PERSISTENCE_UNIT);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Context is not initialized yet.");
        }

        return emf.createEntityManager();
    }
}
