package org.productshop.validation;

import org.productshop.domain.entities.User;
import org.productshop.domain.models.service.UserServiceModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
