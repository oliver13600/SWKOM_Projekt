package io.swagger.services.mapper;


import io.swagger.entity.DocumentTypeEntity;
import io.swagger.model.DocumentType;
import org.mapstruct.Mapper;
import io.swagger.entity.UserInfoEntity;
import io.swagger.model.UserInfo;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserInfoMapper {

    UserInfo toDTO(UserInfoEntity entity);

    UserInfoEntity toEntity(UserInfo dto);

    // Utilize utility methods from BaseMapper if needed
}



