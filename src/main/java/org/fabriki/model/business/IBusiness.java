package org.fabriki.model.business;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;

/**
 * Interface padrão da camada negocial de um módulo.
 *
 * @param <E>
 *            tipo da Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 * 
 */
public interface IBusiness<E, P extends Serializable> {
    E adicionar(E entidade) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao;

    List<E> listar();

    List<E> listar(int primeiroIndice, int tamanhoPagina);
    
    List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros);

    void remover(E entidade) throws EntidadeNaoEncontradaExcecao;

    void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;

    E atualizar(E entidade) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao;

    E recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;

    long getTotalCount();

    long getTotalCount(Map<String, Object> filtros);

    long getTotalCount(Map<String, Object> filtros, boolean andClause);

    List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause);

}
