package com.project.musinsa.model.convertor;

import com.project.musinsa.api.response.model.LowestModel;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.dto.CodiModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CodiConvertor {
    CodiModel toDto(CodiEntity entity);
    CodiEntity toEntity(CodiModel model);

    List<CodiModel> toDtoList(List<CodiEntity> list);
    LowestModel toLowest(CodiModel model);
}
