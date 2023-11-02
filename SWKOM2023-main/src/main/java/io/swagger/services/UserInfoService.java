package io.swagger.services;

import io.swagger.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {

    UserInfo createUser(UserInfo userInfo);

    Optional<UserInfo> getUserByUsername(String username);

    List<UserInfo> getAllUsers();

    UserInfo updateUser(String username, UserInfo userInfo);

    void deleteUser(String username);
}

