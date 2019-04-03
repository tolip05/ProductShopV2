package org.productshop.service;

import org.productshop.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);
    UserServiceModel findUserByUserName(String username);
    UserServiceModel editProfile(UserServiceModel userServiceModel,String oldPassword);
    List<UserServiceModel> findAllUsers();
    void setUserRole(String id,String role);
}
