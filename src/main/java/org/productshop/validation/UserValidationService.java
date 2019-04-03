package org.productshop.validation;

import org.productshop.domain.entities.User;
import org.productshop.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
