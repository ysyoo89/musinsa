package com.project.musinsa.model.convertor;

import com.project.musinsa.api.request.CodiRequest;
import com.project.musinsa.api.response.model.LowestModel;
import com.project.musinsa.entity.CodiEntity;
import com.project.musinsa.model.dto.CodiModel;
import com.project.musinsa.repository.CodiRepository;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CodiConvertor {
    CodiModel toDto(CodiEntity entity);
    CodiEntity toEntity(CodiModel model);

    List<CodiModel> toDtoList(List<CodiEntity> list);
    LowestModel toLowest(CodiModel model);
    CodiEntity createToEntity(CodiRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CodiEntity updateToEntity(@MappingTarget CodiEntity entity, CodiRequest request);
}
