package org.fabriki.model.business.facade;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.fabriki.dto.InstituicaoDto;
import org.fabriki.dto.mapper.IInstituicaoMapper;
import org.fabriki.model.business.InstituicaoBusiness;
import org.fabriki.model.persistence.entity.Instituicao;

/**
 * Fachada de Instituicao.
 * 
 * @see org.fabriki.model.business.facade.AbstractBusinessFacade
 */
public class InstituicaoBusinessFacade extends AbstractBusinessFacade<Instituicao, InstituicaoDto, Long> {
    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void init() {
        mapper = IInstituicaoMapper.INSTANCE;
    }

    @Override
    protected InstituicaoBusiness getBusiness() {
        return (InstituicaoBusiness) business;
    }

    public long getTotalCountPorUserLogin(String userLogin) {
        return getBusiness().getTotalCountPorUserLogin(userLogin);
    }

    public InstituicaoDto recuperarPorNome(String nomeInstituicao) {
        return mapper.converterParaDto(getBusiness().recuperarPorNome(nomeInstituicao));
    }

    public List<InstituicaoDto> listarPorUserLogin(String userLogin, int primeiroIndice, int tamanhoPagina) {
        return mapper.converterParaDtos(getBusiness().listarPorUserLogin(userLogin, primeiroIndice, tamanhoPagina));
    }

    public List<InstituicaoDto> listarParaCompartilhamento(String userLogin, String nomeInstituicao) {
        return mapper.converterParaDtos(getBusiness().listarParaCompartilhamento(userLogin, nomeInstituicao));
    }

    public long getTotalCountHabilitados(Map<String, Object> filtros) {
        return getBusiness().getTotalCountHabilitados(filtros);
    }

    public List<InstituicaoDto> listarHabilitados(int primeiroIndice, int tamanhoPagina, Map<String, Object> filtros) {
        return mapper.converterParaDtos(getBusiness().listarHabilitados(primeiroIndice, tamanhoPagina, filtros));
    }

}
