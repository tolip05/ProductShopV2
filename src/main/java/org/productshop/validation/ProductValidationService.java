package org.productshop.validation;

import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValid(Product product);

    boolean isValid(ProductServiceModel productServiceModel);
}
