package io.swagger.services.mapper;


import io.swagger.entity.DocTagEntity;

import org.mapstruct.Mapper;
import io.swagger.model.DocTag;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DocTagMapper {

    DocTag toDTO(DocTagEntity entity);

    DocTagEntity toEntity(DocTag dto);

    // Utilize utility methods from BaseMapper if needed
}

