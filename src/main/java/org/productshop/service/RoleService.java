package org.productshop.service;

import org.productshop.domain.models.service.RoleServiceModel;
import org.productshop.domain.models.service.UserServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();
   // void assignUserRole(UserServiceModel userServiceModel,long numberOfUsers);
    Set<RoleServiceModel> findAllRoles();
    RoleServiceModel findAuthority(String authority);
}
