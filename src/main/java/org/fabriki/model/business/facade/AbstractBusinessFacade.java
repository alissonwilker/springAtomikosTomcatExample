package org.fabriki.model.business.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.fabriki.dto.mapper.IGenericMapper;
import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;
import org.fabriki.model.business.IBusiness;

/**
 * Classe abstrata que implementa comportamento padrão de uma fachada de módulo.
 *
 * @param <E>
 *            tipo da Entidade.
 * @param <D>
 *            tipo do DTO que representa a Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 * 
 * @see org.fabriki.model.business.facade.IBusinessFacade
 */
public abstract class AbstractBusinessFacade<E, D, P extends Serializable> implements IBusinessFacade<D, P> {
    private static final long serialVersionUID = 1L;

    @Inject
    protected IBusiness<E, P> business;

    protected IGenericMapper<E, D> mapper;
    
    protected abstract IBusiness<E, P> getBusiness();

    @Override
    public D adicionar(D dto) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao {
        return mapper.converterParaDto(business.adicionar(mapper.converterParaEntidade(dto)));
    }

    @Override
    public List<D> listar() {
        return mapper.converterParaDtos(business.listar());
    }
    
    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina) {
        return mapper.converterParaDtos(business.listar(primeiroIndice, tamanhoPagina));
    }

    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return listar(primeiroIndice, tamanhoPagina, filtros, true);
    }

    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause) {
        return mapper.converterParaDtos(business.listar(primeiroIndice, tamanhoPagina, filtros, andClause));
    }

    @Override
    public void remover(D dto) throws EntidadeNaoEncontradaExcecao {
        business.remover(mapper.converterParaEntidade(dto));
    }

    @Override
    public void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        business.remover(chavePrimaria);
    }

    @Override
    public D atualizar(D dto) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao {
        return mapper.converterParaDto(business.atualizar(mapper.converterParaEntidade(dto)));
    }

    @Override
    public D recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        return mapper.converterParaDto(business.recuperar(chavePrimaria));
    }
    
    @Override
    public long getTotalCount() {
        return business.getTotalCount();
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros) {
        return getTotalCount(filtros, true);
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros, boolean andClause) {
        return business.getTotalCount(filtros, andClause);
    }

}
