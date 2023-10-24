package io.swagger.services.mapper;


import org.mapstruct.Mapper;
import io.swagger.entity.UserInfoEntity;
import io.swagger.model.UserInfo;
import org.springframework.stereotype.Component;

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



