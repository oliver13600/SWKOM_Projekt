package io.swagger.services.mapper;

import io.swagger.entity.DocumentEntity;
import io.swagger.services.dto.DocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends BaseMapper<DocumentDTO, DocumentEntity> {
    // No additional methods needed unless there are other specific transformations for Document.
}
