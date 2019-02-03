package org.fabriki.dto.mapper;

import org.fabriki.dto.InstituicaoDto;
import org.fabriki.model.persistence.entity.Instituicao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IInstituicaoMapper extends IGenericMapper<Instituicao, InstituicaoDto> {
    IInstituicaoMapper INSTANCE = Mappers.getMapper(IInstituicaoMapper.class);

}
