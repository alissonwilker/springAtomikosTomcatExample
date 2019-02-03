package org.fabriki.model.persistence.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public interface IEntidade extends Serializable {

    long getId();

    void setId(@NotNull @Max(Long.MAX_VALUE) long id);
}
