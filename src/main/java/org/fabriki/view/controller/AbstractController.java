package org.fabriki.view.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.fabriki.excecao.EntidadeEmUsoExcecao;
import org.fabriki.excecao.EntidadeJaExisteExcecao;
import org.fabriki.excecao.EntidadeNaoEncontradaExcecao;
import org.fabriki.model.business.facade.IBusinessFacade;
import org.fabriki.view.utils.FacesMessageUtils;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Classe abstrata que representa um Controlador chamado pela página web para acionar o Modelo. Este
 * também é responsável pelos fluxos de navegação de páginas.
 *
 * @param <D>
 *            tipo do DTO que representa a Entidade.
 * @param <P>
 *            tipo da chave primária da Entidade.
 */
public abstract class AbstractController<D, P extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    protected IBusinessFacade<D, P> businessFacade;
    protected DefaultLazyDataModel dataModel;

    class DefaultLazyDataModel extends LazyDataModel<D> {
        private static final long serialVersionUID = 1L;

        private Integer actualRowCount;
        private boolean dirty;

        public DefaultLazyDataModel() {
            atualizarRowCount();
        }

        protected int carregarTotalEntidades() {
            return (int) businessFacade.getTotalCount();
        }

        protected int carregarTotalEntidadesFiltradas(Map<String, Object> filters) {
            return (int) businessFacade.getTotalCount(filters);
        }

        protected List<D> carregarEntidades(int first, int pageSize) {
            return businessFacade.listar(first, pageSize);
        }

        protected List<D> carregarEntidades(int first, int pageSize, Map<String, Object> filters) {
            return businessFacade.listar(first, pageSize, filters);
        }

        private void atualizarRowCount() {
            setRowCount(carregarTotalEntidades());

            if (actualRowCount == null) {
                actualRowCount = getRowCount();
            }

            if (actualRowCount != getRowCount()) {
                dirty = true;
            }
        }

        public boolean isDirty() {
            return dirty;
        }

        public void refresh() {
            atualizarRowCount();
        }

        @Override
        public List<D> load(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
            
            if (filters != null && filters.size() > 0) {
                int totalRowCount = carregarTotalEntidadesFiltradas(filters);
                this.setRowCount(totalRowCount);
                if (totalRowCount > 0 && first >= totalRowCount) {
                    int numberOfPages = (int) Math.ceil(totalRowCount * 1d / pageSize);
                    first = Math.max((numberOfPages - 1) * pageSize, 0);
                }
                return carregarEntidades(first, pageSize, filters);
            } else {
                int totalRowCount = carregarTotalEntidades();
                this.setRowCount(totalRowCount);
                if (totalRowCount > 0 && first >= totalRowCount) {
                    int numberOfPages = (int) Math.ceil(totalRowCount * 1d / pageSize);
                    first = Math.max((numberOfPages - 1) * pageSize, 0);
                }
                return carregarEntidades(first, pageSize);
            }
        }
    }

    @PostConstruct
    public void init() {
        dataModel = criarDataModel();
    }

    protected abstract IBusinessFacade<D, P> getBusinessFacade();

    protected DefaultLazyDataModel criarDataModel() {
        return new DefaultLazyDataModel();
    }

    public DefaultLazyDataModel getDataModel() {
        return dataModel;
    }

    public void refreshDataModel() {
        dataModel.refresh();
        if (dataModel.isDirty()) {
            dataModel = criarDataModel();
        }
    }

    /**
     * Cadastra uma Entidade representada pelo DTO.
     * 
     * @param dto
     *            o DTO que representa a Entidade a ser adicionada.
     * @return <i>true</i> se a Entidade foi adicionada com sucesso. <i>false</i>, caso contrário.
     */
    public D adicionar(D dto) {
        try {
            dto = businessFacade.adicionar(dto);
            adicionarMensagemSucesso();
            return dto;
        } catch (EntidadeJaExisteExcecao e) {
            FacesMessageUtils.addInfoFacesMessage("excecao.itemJaCadastrado");
            return null;
        } catch (EntidadeNaoEncontradaExcecao e) {
            FacesMessageUtils.addInfoFacesMessage("excecao.itemNaoEncontrado");
            return null;
        }
    }

    /**
     * Atualiza uma Entidade representada pelo DTO.
     * 
     * @param dto
     *            o DTO que representa a Entidade a ser atualizada.
     * @return <i>true</i> se a Entidade foi atualizada com sucesso. <i>false</i>, caso contrário.
     */
    public D atualizar(D dto) {
        try {
            dto = businessFacade.atualizar(dto);
            adicionarMensagemSucesso();
            return dto;
        } catch (EntidadeJaExisteExcecao e) {
            FacesMessageUtils.addInfoFacesMessage("excecao.itemJaCadastrado");
            return null;
        } catch (EntidadeNaoEncontradaExcecao e) {
            FacesMessageUtils.addInfoFacesMessage("excecao.itemNaoEncontrado");
            return null;
        }
    }

    /**
     * Remove uma Entidade representada pelo DTO.
     * 
     * @param dto
     *            o DTO que representa a Entidade a ser removida.
     */
    public void remover(D dto) {
        try {
            businessFacade.remover(dto);
            adicionarMensagemSucesso();
        } catch (EntidadeNaoEncontradaExcecao e) {
            adicionarMensagemItemNaoEncontrado();
        } catch (EntidadeEmUsoExcecao pe) {
            adicionarMensagemItemEmUso();
        }

    }

    private void adicionarMensagemItemEmUso() {
        FacesMessageUtils.addInfoFacesMessage("app.item.exclusao.emUso");
    }

    private void adicionarMensagemItemNaoEncontrado() {
        FacesMessageUtils.addInfoFacesMessage("excecao.itemNaoEncontrado");
    }

    private void adicionarMensagemSucesso() {
        FacesMessageUtils.addInfoFacesMessage("app.sucesso");
    }

}
