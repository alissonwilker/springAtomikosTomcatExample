package org.fabriki.model.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;
import org.fabriki.model.persistence.dao.IDao;

/**
 * Classe abstrata que contém implementação de comportamento padrão da camada negocial de um módulo.
 *
 * @param <E>
 *            tipo da Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 * 
 * @see org.fabriki.model.business.IBusiness
 */
public abstract class AbstractBusiness<E, P extends Serializable> implements IBusiness<E, P>, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected IDao<E, P> dao;

    protected abstract IDao<E, P> getDao();

    @Override
    public E adicionar(E entidade) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao {
        return dao.adicionar(entidade);
    }

    @Override
    public void remover(E entidade) throws EntidadeNaoEncontradaExcecao {
        dao.remover(entidade);
    }

    @Override
    public void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        dao.remover(chavePrimaria);
    }

    @Override
    public E atualizar(E entidade) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao {
        return dao.atualizar(entidade);
    }

    @Override
    public List<E> listar() {
        return dao.listar();
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina) {
        return dao.listar(primeiroIndice, tamanhoPagina);
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return listar(primeiroIndice, tamanhoPagina, filtros, true);
    }

    @Override
    public E recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        return dao.recuperar(chavePrimaria);
    }

    @Override
    public long getTotalCount() {
        return dao.getTotalCount();
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros) {
        return getTotalCount(filtros, true);
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros, boolean andClause) {
        return dao.getTotalCount(filtros, andClause);
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause) {
        return dao.listar(primeiroIndice, tamanhoPagina, filtros, andClause);
    }

}
