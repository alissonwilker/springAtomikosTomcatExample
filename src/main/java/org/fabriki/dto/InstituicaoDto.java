package org.fabriki.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class InstituicaoDto implements IDto {
    private static final long serialVersionUID = 1L;

    private long id;
    private String nome;
    private Boolean habilitado;

    public InstituicaoDto() {
    }

    public InstituicaoDto(String nome) {
        this();
        this.nome = nome;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(nome);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InstituicaoDto)) {
            return false;
        }
        InstituicaoDto that = (InstituicaoDto) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(nome, that.nome);
        return eb.isEquals();
    }

}