package org.fabriki.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public interface IDto extends Serializable {

    long getId();

    void setId(@NotNull @Max(Long.MAX_VALUE) long id);
}