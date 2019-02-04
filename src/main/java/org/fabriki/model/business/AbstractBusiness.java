package org.fabriki.model.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;
import org.fabriki.model.persistence.dao.IDao;
import org.springframework.transaction.annotation.Transactional;

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
public abstract class AbstractBusiness<E, P extends Serializable> implements IBusiness<E, P> {

    protected abstract IDao<E, P> getDao();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E adicionar(E entidade) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao {
        return getDao().adicionar(entidade); 
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remover(E entidade) throws EntidadeNaoEncontradaExcecao {
    	getDao().remover(entidade);
    } 

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
    	getDao().remover(chavePrimaria);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public E atualizar(E entidade) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao {
        return getDao().atualizar(entidade);
    }

    @Override
    public List<E> listar() {
        return getDao().listar();
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina) {
        return getDao().listar(primeiroIndice, tamanhoPagina);
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return listar(primeiroIndice, tamanhoPagina, filtros, true);
    }

    @Override
    public E recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        return getDao().recuperar(chavePrimaria);
    }

    @Override
    public long getTotalCount() {
        return getDao().getTotalCount();
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros) {
        return getTotalCount(filtros, true);
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros, boolean andClause) {
        return getDao().getTotalCount(filtros, andClause);
    }

    @Override
    public List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause) {
        return getDao().listar(primeiroIndice, tamanhoPagina, filtros, andClause);
    }

}
