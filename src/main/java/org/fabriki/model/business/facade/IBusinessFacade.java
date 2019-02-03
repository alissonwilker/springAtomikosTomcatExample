package org.fabriki.model.business.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;

/**
 * Interface de uma fachada para a camada negocial de um módulo. A fachada é o
 * ponto de entrada para a camada negocial do módulo. Normalmente utilizada por
 * Controladores ou pela API.
 *
 * @param <D>
 *            tipo do DTO.
 * @param <P>
 *            tipo da chave primária da Entidade representada pelo DTO.
 * 
 * @see org.fabriki.model.business.IBusiness
 */
public interface IBusinessFacade<D, P extends Serializable> extends Serializable {
    
    D adicionar(D dto) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao;

    List<D> listar();
    
    List<D> listar(int primeiroIndice, int tamanhoPagina);

    void remover(D dto) throws EntidadeNaoEncontradaExcecao;

    void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;

    D atualizar(D dto) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao;

    D recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;
    
    long getTotalCount();

    List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros);
    
    List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause);

    long getTotalCount(Map<String, Object> filtros);

    long getTotalCount(Map<String, Object> filtros, boolean andClause);

}
