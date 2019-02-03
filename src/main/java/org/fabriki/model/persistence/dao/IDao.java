package org.fabriki.model.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;

/**
 * Interface padrão de um DAO.
 *
 * @param <E>
 *            tipo da Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 */
public interface IDao<E, P extends Serializable> {

    /**
     * Adiciona um registro da Entidade na base de dados.
     * 
     * @param entidade
     *            a Entidade a ser adicionada na base.
     * @return um objeto gerenciado da Entidade adicionada.
     * @throws EntidadeJaExisteExcecao
     *             se a Entidade já existir na base.
     */
    E adicionar(E entidade) throws EntidadeJaExisteExcecao;

    /**
     * Lista todos os registros da Entidade que existem na base da dados.
     * 
     * @return uma lista com os registros da Entidade.
     */
    List<E> listar();

    /**
     * Lista os registros da Entidade com paginação.
     * 
     * @param primeiroIndice
     *            primeira posição para os elementos da página.
     * @param tamanhoPagina
     *            o tamanho da página.
     * @return uma lista com um subconjunto dos registros da Entidade.
     */
    List<E> listar(int primeiroIndice, int tamanhoPagina);

    /**
     * Remove uma Entidade da base de dados.
     * 
     * @param entidade
     *            a Entidade a ser removida.
     * @throws EntidadeNaoEncontradaExcecao
     *             se a Entidade não for encontrada para remoção.
     */
    void remover(E entidade) throws EntidadeNaoEncontradaExcecao;

    /**
     * Remove uma Entidade a partir da chave primária.
     * 
     * @param chavePrimaria
     *            a chave primária da Entidade a ser removida.
     * @throws EntidadeNaoEncontradaExcecao
     *             se a Entidade correspondente à chave primária não for encontrada.
     */
    void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;

    /**
     * Atualiza o registro de uma Entidade na base de dados.
     * 
     * @param entidade
     *            a Entidade gerenciada a ser atualizada na base.
     * @return um objeto gerenciado da Entidade atualizada.
     * @throws EntidadeNaoEncontradaExcecao
     *             se a Entidade não for encontrada na base para atualização.
     * @throws EntidadeJaExisteExcecao
     *             se ocorrer violação de constraint.
     */
    E atualizar(E entidade) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao;

    /**
     * Recupera uma Entidade da base de dados a partir da chave primária.
     * 
     * @param chavePrimaria
     *            a chave primária da Entidade a ser recuperada.
     * @return um objeto gerenciado da Entidade recuperada.
     * @throws EntidadeNaoEncontradaExcecao
     *             se a Entidade não for encontrada na base.
     */
    E recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao;

    /**
     * Recupera o número total de registros da entidade na base de dados.
     * 
     * @return o número total de registros da entidade na base de dados.
     */
    long getTotalCount();

    List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros);

    long getTotalCount(Map<String, Object> filtros);

    long getTotalCount(Map<String, Object> filtros, boolean andClause);

    List<E> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause);

}
