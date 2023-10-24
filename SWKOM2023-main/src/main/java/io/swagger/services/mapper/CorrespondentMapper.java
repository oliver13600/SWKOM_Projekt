package io.swagger.services.mapper;

import io.swagger.entity.CorrespondentEntity;
import io.swagger.model.Correspondent;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

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