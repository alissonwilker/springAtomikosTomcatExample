package org.fabriki.view.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.fabriki.dto.InstituicaoDto;
import org.fabriki.excecao.EntidadeEmUsoExcecao;
import org.fabriki.model.business.facade.InstituicaoBusinessFacade;
import org.fabriki.view.utils.FacesMessageUtils;
import org.fabriki.view.utils.JsfUtils;
import org.fabriki.view.utils.JsfUtils.Pagina;

/**
 * Controller de Instituicao.
 * 
 * @see org.fabriki.view.controller.AbstractController
 */
@Named
public class InstituicaoController extends AbstractFabrikiController<InstituicaoDto, Long> {
    private static final long serialVersionUID = 1L;

    private InstituicaoDto instituicao;

    private String nomeInstituicao;
    private Boolean habilitadoInstituicao;

    private String tituloDialogo;
    private String tituloPainel;

    class InstituicaoLazyDataModel extends DefaultLazyDataModel {
        private static final long serialVersionUID = 1L;

        @Override
        public int carregarTotalEntidadesFiltradas(Map<String, Object> filtros) {
            return (int) getBusinessFacade().getTotalCount(filtros);
        }

        @Override
        public List<InstituicaoDto> carregarEntidades(int primeiroIndice, int tamanhoPagina,
            Map<String, Object> filtros) {
            return getBusinessFacade().listar(primeiroIndice, tamanhoPagina, filtros);
        }

        @Override
        public int carregarTotalEntidades() {
            return (int) getBusinessFacade().getTotalCount();
        }

        @Override
        public List<InstituicaoDto> carregarEntidades(int primeiroIndice, int tamanhoPagina) {
            return getBusinessFacade().listar(primeiroIndice, tamanhoPagina);
        }
    }

    @Override
    protected DefaultLazyDataModel criarDataModel() {
        return new InstituicaoLazyDataModel();
    }

    @Override
    protected InstituicaoBusinessFacade getBusinessFacade() {
        return (InstituicaoBusinessFacade) businessFacade;
    }

    public String getTituloDialogo() {
        return tituloDialogo;
    }

    public void setTituloDialogo(String tituloDialogo) {
        this.tituloDialogo = tituloDialogo;
    }

    public String getTituloPainel() {
        return tituloPainel;
    }

    public void setTituloPainel(String tituloPainel) {
        this.tituloPainel = tituloPainel;
    }

    public InstituicaoDto getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(InstituicaoDto instituicao) {
        this.instituicao = instituicao;
        this.nomeInstituicao = instituicao.getNome();
        this.habilitadoInstituicao = instituicao.getHabilitado();
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public Boolean getHabilitadoInstituicao() {
        return habilitadoInstituicao;
    }

    public void setHabilitadoInstituicao(Boolean habilitadoInstituicao) {
        this.habilitadoInstituicao = habilitadoInstituicao;
    }

    public long getIdInstituicao() {
        if (instituicao != null) {
            return instituicao.getId();
        }
        return -1;
    }

    public void setIdInstituicao(long idInstituicao) throws IOException {
        if (instituicao == null && idInstituicao > 0) {
            instituicao = getBusinessFacade().recuperar(idInstituicao);
            if (instituicao == null) {
                JsfUtils.redirecionar(Pagina.index);
            }
        }
    }

    public void limparInstituicao() {
        this.instituicao = null;
        this.nomeInstituicao = null;
        this.habilitadoInstituicao = null;
    }

    public void criarInstituicao() {
        InstituicaoDto instituicao = new InstituicaoDto(nomeInstituicao);
        instituicao.setHabilitado(false);

        instituicao = adicionar(instituicao);

        limparInstituicao();
    }

    @Override
    public void remover(InstituicaoDto instituicao) {
        try {
            super.remover(instituicao);
        } catch (EntidadeEmUsoExcecao pe) {
            FacesMessageUtils.addInfoFacesMessage("app.item.exclusao.emUso");
        }
        refreshDataModel();
    }

}