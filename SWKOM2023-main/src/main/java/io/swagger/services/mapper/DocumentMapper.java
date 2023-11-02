package io.swagger.services.mapper;


import io.swagger.entity.DocumentEntity;

import io.swagger.model.Document;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface DocumentMapper {

    Document toDTO(DocumentEntity entity);

    DocumentEntity toEntity(Document dto);

    // Utilize utility methods from BaseMapper if needed
}




// TODO ugyanezen az elven csinald meg a tobbi mappert, servicet, serviceimplt, repositoryt, entityt