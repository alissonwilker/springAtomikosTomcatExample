package org.fabriki.model.business.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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
    
    protected abstract IGenericMapper<E, D> getMapper();
    
    protected abstract IBusiness<E, P> getBusiness();

    @Override
    public D adicionar(D dto) throws EntidadeJaExisteExcecao, EntidadeNaoEncontradaExcecao {
        return getMapper().converterParaDto(getBusiness().adicionar(getMapper().converterParaEntidade(dto)));
    }

    @Override
    public List<D> listar() {
        return getMapper().converterParaDtos(getBusiness().listar());
    }
    
    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina) {
        return getMapper().converterParaDtos(getBusiness().listar(primeiroIndice, tamanhoPagina));
    }

    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return listar(primeiroIndice, tamanhoPagina, filtros, true);
    }

    @Override
    public List<D> listar(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros, boolean andClause) {
        return getMapper().converterParaDtos(getBusiness().listar(primeiroIndice, tamanhoPagina, filtros, andClause));
    }

    @Override
    public void remover(D dto) throws EntidadeNaoEncontradaExcecao {
    	getBusiness().remover(getMapper().converterParaEntidade(dto));
    }

    @Override
    public void remover(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
    	getBusiness().remover(chavePrimaria);
    }

    @Override
    public D atualizar(D dto) throws EntidadeNaoEncontradaExcecao, EntidadeJaExisteExcecao {
        return getMapper().converterParaDto(getBusiness().atualizar(getMapper().converterParaEntidade(dto)));
    }

    @Override
    public D recuperar(P chavePrimaria) throws EntidadeNaoEncontradaExcecao {
        return getMapper().converterParaDto(getBusiness().recuperar(chavePrimaria));
    }
    
    @Override
    public long getTotalCount() {
        return getBusiness().getTotalCount();
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros) {
        return getTotalCount(filtros, true);
    }

    @Override
    public long getTotalCount(Map<String, Object> filtros, boolean andClause) {
        return getBusiness().getTotalCount(filtros, andClause);
    }

}
