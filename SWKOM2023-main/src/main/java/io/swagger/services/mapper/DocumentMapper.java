package io.swagger.services.mapper;


import io.swagger.entity.DocumentEntity;

import io.swagger.model.Document;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public class DocumentMapper implements BaseMapper<Document, DocumentEntity> {
    @Override
    public Document toDTO(DocumentEntity entity) {
        return null; // TODO
    }

    @Override
    public DocumentEntity toEntity(Document dto) {
        return null; // TODO
    }
}




// TODO ugyanezen az elven csinald meg a tobbi mappert, servicet, serviceimplt, repositoryt, entityt