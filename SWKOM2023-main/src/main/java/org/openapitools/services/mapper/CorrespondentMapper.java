package org.openapitools.services.mapper;

import org.openapitools.entity.CorrespondentEntity;
import org.openapitools.model.Correspondent;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public class CorrespondentMapper implements BaseMapper<Correspondent, CorrespondentEntity> {

    @Override
    public Correspondent toDTO(CorrespondentEntity entity) {
        return null; // TODO
    }

    @Override
    public CorrespondentEntity toEntity(Correspondent dto) {
        return null; // TODO
    }


}

// TODO ugyanezen az elven csinald meg a tobbi mappert, servicet, serviceimplt, repositoryt, entityt