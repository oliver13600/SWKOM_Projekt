package io.swagger.services.mapper;

import io.swagger.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;
import io.swagger.model.DocumentType;
import org.springframework.stereotype.Component;

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


