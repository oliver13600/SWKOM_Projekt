package io.swagger.services.mapper;


import io.swagger.entity.DocTagEntity;

import org.mapstruct.Mapper;
import io.swagger.model.DocTag;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public class DocTagMapper implements BaseMapper<DocTag, DocTagEntity> {

    @Override
    public DocTag toDTO(DocTagEntity entity) {
        return null; // TODO
    }

    @Override
    public DocTagEntity toEntity(DocTag dto) {
        return null; // TODO
    }


}

