package io.swagger.services.mapper;

import io.swagger.entity.CorrespondentEntity;
import io.swagger.model.Correspondent;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CorrespondentMapper {


    public Correspondent toDTO(CorrespondentEntity entity);


    public CorrespondentEntity toEntity(Correspondent dto);


}

// TODO ugyanezen az elven csinald meg a tobbi mappert, servicet, serviceimplt, repositoryt, entityt