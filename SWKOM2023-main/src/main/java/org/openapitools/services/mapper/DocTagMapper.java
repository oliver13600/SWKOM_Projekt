package org.openapitools.services.mapper;


import org.openapitools.entity.CorrespondentEntity;
import org.openapitools.entity.DocTagEntity;
import org.openapitools.model.Correspondent;

import org.mapstruct.Mapper;
import org.openapitools.model.DocTag;
import org.springframework.stereotype.Component;

import java.util.List;

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

