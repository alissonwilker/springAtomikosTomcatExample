package org.fabriki.model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@NamedQueries({
@NamedQuery(name = "Instituicao.getTotalCountHabilitados", query = "SELECT count(i.id) FROM Instituicao i WHERE i.habilitado = 'true'"),
@NamedQuery(name = "Instituicao.listarHabilitados", query = "SELECT i FROM Instituicao i WHERE i.habilitado = 'true'"),
@NamedQuery(name = "Instituicao.getTotalCountHabilitadosPorNome", query = "SELECT count(i.id) FROM Instituicao i WHERE i.habilitado = 'true' AND LOWER(i.nome) LIKE :nomeInstituicao"),
@NamedQuery(name = "Instituicao.listarHabilitadosPorNome", query = "SELECT i FROM Instituicao i WHERE i.habilitado = 'true' AND LOWER(i.nome) LIKE :nomeInstituicao"),  
@NamedQuery(name = "Instituicao.recuperarPorNome", query = "SELECT i FROM Instituicao i WHERE i.nome = :nomeInstituicao") }) 
@Entity
@Table(name = "tb_instituicao", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Instituicao implements IEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true, length = 19)
    @NotNull
    @Max(Long.MAX_VALUE)
    private long id;

    @Column(name = "nome", updatable = true, nullable = false, unique = true, length = 39)
    @NotNull
    private String nome;
    
    @Column(name = "habilitado", columnDefinition = "boolean default false", updatable = true, nullable = true, unique = false)
    private Boolean habilitado;

    @Valid
    public Instituicao() {
    }

    @Valid
    public Instituicao(String nome) {
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

    public void setNome(@NotNull @Size(max = 39) String nome) {
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
        if (!(obj instanceof Instituicao)) {
            return false;
        }
        Instituicao that = (Instituicao) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(nome, that.nome);
        return eb.isEquals();
    }

}