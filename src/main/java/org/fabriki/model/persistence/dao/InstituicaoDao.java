package org.fabriki.model.persistence.dao;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.fabriki.log.FabrikiLoggerFactory;
import org.fabriki.model.persistence.entity.Instituicao;
import org.slf4j.Logger;

/**
 * DAO de Instituicao.
 * 
 * @see org.fabriki.model.persistence.dao.AbstractFabrikiDao
 */
public class InstituicaoDao extends AbstractFabrikiDao<Instituicao, Long> {
    private static final long serialVersionUID = 1L;
    protected static final Logger logger = FabrikiLoggerFactory.getLogger(MethodHandles.lookup().lookupClass(),
        LOG_TAG_PREFIX);

    @Override
    protected Logger getLogger() {
        return logger;
    }

    public Instituicao recuperarPorNome(String nomeInstituicao) {
        logger.info("Recuperando instituicao por nome...");
        Query query = entityManager.createNamedQuery("Instituicao.recuperarPorNome");
        query.setParameter("nomeInstituicao", nomeInstituicao);
        return (Instituicao) query.getSingleResult();
    }

    public long getTotalCountPorUserLogin(String userLogin) {
        logger.info("Recuperando total de instituicoes de um userLogin...");
        Query query = entityManager.createNamedQuery("Instituicao.getTotalCountPorUserLogin");
        query.setParameter("userLogin", userLogin);
        return (long) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Instituicao> listarPorUserLogin(String userLogin, int primeiroIndice, int tamanhoPagina) {
        logger.info("Listando instituicoes por user login...");
        Query query = entityManager.createNamedQuery("Instituicao.listarPorUserLogin");
        query.setParameter("userLogin", userLogin);
        query.setFirstResult(primeiroIndice);
        query.setMaxResults(tamanhoPagina);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Instituicao> listarParaCompartilhamento(String userLogin, String nomeInstituicao) {
        logger.info("Listando instituicoes por user login sem paginação...");
        Query query = entityManager.createNamedQuery("Instituicao.listarParaCompartilhamento");
        query.setParameter("userLogin", userLogin);
        query.setParameter("nomeInstituicao", nomeInstituicao);
        return query.getResultList();
    }

    public long getTotalCountHabilitados(Map<String, Object> filtros) {
        logger.info("Recuperando total de instituicoes habilitadas...");
        Query query = null;
        if (filtros != null && filtros.size() > 0) {
            query = entityManager.createNamedQuery("Instituicao.getTotalCountHabilitadosPorNome");
            query.setParameter("nomeInstituicao", "%" + ((String)filtros.get("nome")).toLowerCase() + "%");
        } else  {
            query = entityManager.createNamedQuery("Instituicao.getTotalCountHabilitados");
        }
        return (long) query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<Instituicao> listarHabilitados(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        logger.info("Listando instituicoes habilitadas...");
        
        Query query = null;
        if (filtros != null && filtros.size() > 0) {
            query = entityManager.createNamedQuery("Instituicao.listarHabilitadosPorNome");
            query.setParameter("nomeInstituicao", "%" + ((String)filtros.get("nome")).toLowerCase() + "%");
        } else  {
            query = entityManager.createNamedQuery("Instituicao.listarHabilitados");
        }
        query.setFirstResult(primeiroIndice);
        query.setMaxResults(tamanhoPagina);
        return query.getResultList();
    }

}
