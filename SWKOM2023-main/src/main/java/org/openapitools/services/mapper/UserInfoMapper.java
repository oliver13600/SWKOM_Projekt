package org.openapitools.services.mapper;


import org.openapitools.entity.DocumentTypeEntity;
import org.mapstruct.Mapper;
import org.openapitools.entity.UserInfoEntity;
import org.openapitools.model.DocumentType;
import org.openapitools.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public class UserInfoMapper implements BaseMapper<UserInfo, UserInfoEntity> {

    @Override
    public UserInfo toDTO(UserInfoEntity entity) {
        return null; // TODO
    }

    @Override
    public UserInfoEntity toEntity(UserInfo dto) {
        return null; // TODO
    }


}



