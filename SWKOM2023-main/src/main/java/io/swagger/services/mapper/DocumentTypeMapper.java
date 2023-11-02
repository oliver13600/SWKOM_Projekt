package io.swagger.services.mapper;

import io.swagger.entity.DocumentEntity;
import io.swagger.entity.DocumentTypeEntity;
import io.swagger.model.Document;
import org.mapstruct.Mapper;
import io.swagger.model.DocumentType;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DocumentTypeMapper {

    DocumentType toDTO(DocumentTypeEntity entity);

    DocumentTypeEntity toEntity(DocumentType dto);

    // Utilize utility methods from BaseMapper if needed
}


