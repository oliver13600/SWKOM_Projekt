package io.swagger.services;

import io.swagger.entity.UserInfoEntity;
import io.swagger.model.UserInfo;
import io.swagger.repository.UserInfoRepository;
import io.swagger.services.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo createUser(UserInfo userInfo) {
        // Convert UserInfo to UserInfoEntity before saving, and then convert back when returning.

        UserInfoEntity toSave = userInfoMapper.toEntity(userInfo);
        UserInfoEntity saved = userInfoRepository.save(toSave);
        return userInfoMapper.toDTO(saved);
    }

    @Override
    public Optional<UserInfo> getUserByUsername(String username) {
        Optional<UserInfoEntity> optUserInfoEntity = userInfoRepository.findByUsername(username);
        if (optUserInfoEntity.isPresent()) {
            return Optional.of(userInfoMapper.toDTO(optUserInfoEntity.get()));
        } else{
            return Optional.empty();
        }
    }

    @Override
    public List<UserInfo> getAllUsers() {
        // Convert the list of UserInfoEntity objects to a list of UserInfo objects.

        List<UserInfoEntity> userInfoEntities = userInfoRepository.findAll();
        return userInfoEntities.stream().map(userInfoMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserInfo updateUser(String username, UserInfo userInfo) {
        if (userInfoRepository.existsByUsername(username)) {
            // Convert, save, then convert back.
            //set username
            userInfo.setUsername(username);
            UserInfoEntity toSave = userInfoMapper.toEntity(userInfo);
            UserInfoEntity saved = userInfoRepository.save(toSave);
            return userInfoMapper.toDTO(saved);


        }
        return null;
    }

    @Override
    public void deleteUser(String username) {
        userInfoRepository.deleteByUsername(username);
    }



}

