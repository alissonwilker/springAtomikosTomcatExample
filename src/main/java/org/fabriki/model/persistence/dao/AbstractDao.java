package org.fabriki.model.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.fabriki.excecao.EntidadeEmUsoExcecao;
import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Classe abstrata que implementa comportamento padrão de um DAO.
 *
 * @param <E>
 *            tipo da Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 * 
 * @see org.fabriki.model.persistence.dao.IDao
 */
public abstract class AbstractDao<E, P extends Serializable> implements IDao<E, P> {
    
	protected static final String LOG_TAG_PREFIX = " [SGBD]";

    @Autowired SessionFactory sessionFactory;

    private Class<?> domain;

    protected abstract Logger getLogger();
    
    protected Session getSession() {
    	try {
    		return sessionFactory.getCurrentSession();
    	} catch (HibernateException e) {
    		return sessionFactory.openSession();
    	}
    }

    protected Class<?> getDomainClass() {
        if (this.domain == null) {
            this.domain = getGenericTypeArgument(this.getClass(), 0);
        }
        return this.domain;
    }

    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericTypeArgument(final Class<?> clazz, final int idx) {
        final Type type = clazz.getGenericSuperclass();
        ParameterizedType paramType;
        try {
            paramType = (ParameterizedType) type;
        } catch (ClassCastException cause) {
            paramType = (ParameterizedType) ((Class<T>) type).getGenericSuperclass();
        }
        return (Class<T>) paramType.getActualTypeArguments()[idx];
    }

    @Override
    public E adicionar(E entidade) throws EntidadeJaExisteExcecao {
        getLogger().info("Persistindo entidade...");
        try {
            getSession().save(entidade);
            return entidade;
        } catch (PersistenceException eeex) {
            if (eeex.getCause() instanceof ConstraintViolationException) {
                throw new EntidadeJaExisteExcecao(eeex);
            } else {
                throw eeex;
            }
        }
    }

    @Override
    public void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        getLogger().info("Removendo entidade pela chave primaria...");
        E entidade = recuperar(chavePrimaria);
        remover(entidade);
    }

	@Override
    public void remover(E entidade) throws EntidadeNaoEncontradaExcecao {
        getLogger().info("Removendo entidade...");
        try {
        	Session session = getSession();
//            session.merge(entidade);
            session.delete(entidade);
        } catch (IllegalArgumentException iaex) {
            throw new EntidadeNaoEncontradaExcecao(iaex);
        } catch (PersistenceException pe) {
            if (pe.getCause().getMessage().contains("ConstraintViolationException")) {
                throw new EntidadeEmUsoExcecao(pe);
            }
        }
    }

    @Override
    public E atualizar(E entidade) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao {
        getLogger().info("Atualizando entidade...");
        try {
            getSession().saveOrUpdate(entidade);
            return entidade;
        } catch (IllegalArgumentException iaex) {
            throw new EntidadeNaoEncontradaExcecao(iaex);
        } catch (PersistenceException eeex) {
            if (eeex.getCause() instanceof ConstraintViolationException) {
                throw new EntidadeJaExisteExcecao(eeex);
            } else {
                throw eeex;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        getLogger().info("Recuperando entidade pela chave primaria...");
        E entity = (E) getSession().find(getDomainClass(), chavePrimaria);
        if (entity == null) {
            throw new EntidadeNaoEncontradaExcecao();
        }
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> listar() {
        getLogger().info("Listando entidades...");
        return getSession()
    			.createQuery("FROM " + getDomainClass().getSimpleName() + " ORDER BY id DESC")
    			.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina) {
        getLogger().info("Listando entidades com paginação...");
        Query query = getSession().createQuery("FROM " + getDomainClass().getSimpleName() + " ORDER BY id DESC");
        query.setFirstResult(primeiroIndice);
        query.setMaxResults(tamanhoPagina);
        return query.getResultList();
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return listar(primeiroIndice, tamanhoPagina, filtros, true);
    }

    @Override
    public long getTotalCount() {
        getLogger().info("Recuperando total de registros na tabela...");
        Query query = getSession().createQuery("SELECT count(id) FROM " + getDomainClass().getSimpleName());
        return (long) query.getSingleResult();
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros) {
        return getTotalCount(filtros, true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public long getTotalCount(Map<String, Object> filtros, boolean andClause) {
        getLogger().info("Recuperando total de registros na tabela com filtros...");
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        Class clazz = getDomainClass();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root root = criteriaQuery.from(clazz);
        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));

        if (filtros != null && filtros.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : filtros.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }

                boolean igualdade = false;
                if (field.startsWith("=")) {
                    igualdade = true;
                    field = field.substring(1);
                }

                Path path = null;
                for (String subfield : field.split("\\.")) {
                    path = path == null ? root.get(subfield) : path.get(subfield);
                }
                Expression<String> expr = path.as(String.class);

                Predicate p = null;
                if (igualdade) {
                    p = cb.equal(expr, value.toString());
                } else {
                    p = cb.like(cb.lower(expr), "%" + value.toString().toLowerCase() + "%");
                }
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                if (andClause) {
                    criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                } else {
                    criteriaQuery.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
                }
            }
        }
        Long count = getSession().createQuery(select).getSingleResult();
        return count.intValue();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause) {
        getLogger().info("Listando entidades com paginação e filtros...");

        EntityManager entityManager = (EntityManager) getSession().getDelegate();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        Class clazz = getDomainClass();
        CriteriaQuery criteriaQuery = cb.createQuery(clazz);
        Root root = criteriaQuery.from(clazz);
        CriteriaQuery select = criteriaQuery.select(root);

        if (filtros != null && filtros.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : filtros.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }

                boolean igualdade = false;
                if (field.startsWith("=")) {
                    igualdade = true;
                    field = field.substring(1);
                }

                Path path = null;
                for (String subfield : field.split("\\.")) {
                    path = path == null ? root.get(subfield) : path.get(subfield);
                }
                Expression<String> expr = path.as(String.class);

                Predicate p = null;
                if (igualdade) {
                    p = cb.equal(expr, value.toString());
                } else {
                    p = cb.like(cb.lower(expr), "%" + value.toString().toLowerCase() + "%");
                }
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                if (andClause) {
                    criteriaQuery.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
                } else {
                    criteriaQuery.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
                }
            }
        }

        TypedQuery query = entityManager.createQuery(select);
        query.setFirstResult(primeiroIndice);
        query.setMaxResults(tamanhoPagina);
        List list = query.getResultList();
        return list;
    }

}
