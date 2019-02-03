package org.fabriki.model.business;

import java.util.List;
import java.util.Map;

import org.fabriki.model.persistence.dao.InstituicaoDao;
import org.fabriki.model.persistence.entity.Instituicao;

/**
 * Componente de negócio do Fabriki.
 * 
 * @see org.fabriki.model.business.AbstractBusiness
 */
public class InstituicaoBusiness extends AbstractBusiness<Instituicao, Long> {
    private static final long serialVersionUID = 1L;

    @Override
    protected InstituicaoDao getDao() {
        return (InstituicaoDao) dao;
    }

    public Instituicao recuperarPorNome(String nomeInstituicao) {
        return getDao().recuperarPorNome(nomeInstituicao);
    }

    public long getTotalCountPorUserLogin(String userLogin) {
        return getDao().getTotalCountPorUserLogin(userLogin);
    }

    public List<Instituicao> listarPorUserLogin(String userLogin, int primeiroIndice, int tamanhoPagina) {
        return getDao().listarPorUserLogin(userLogin, primeiroIndice, tamanhoPagina);
    }

    public List<Instituicao> listarParaCompartilhamento(String userLogin, String nomeInstituicao) {
        return getDao().listarParaCompartilhamento(userLogin, nomeInstituicao);
    }

    public long getTotalCountHabilitados(Map<String, Object> filtros) {
        return getDao().getTotalCountHabilitados(filtros);
    }

    public List<Instituicao> listarHabilitados(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return getDao().listarHabilitados(primeiroIndice, tamanhoPagina, filtros);
    }

}