package org.openapitools.services.mapper;

import org.openapitools.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public class DocumentTypeMapper implements BaseMapper<DocumentType, DocumentTypeEntity> {

    @Override
    public DocumentType toDTO(DocumentTypeEntity entity) {
        return null; // TODO
    }

    @Override
    public DocumentTypeEntity toEntity(DocumentType dto) {
        return null; // TODO
    }


}


