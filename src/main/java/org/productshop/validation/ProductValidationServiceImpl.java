package org.productshop.validation;

import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class ProductValidationServiceImpl implements ProductValidationService {
    @Override
    public boolean isValid(Product product) {
        return product != null;
    }

    @Override
    public boolean isValid(ProductServiceModel productServiceModel) {
        return productServiceModel != null &&
                areCategoriesValid(productServiceModel.getCategories());

    }

    private boolean areCategoriesValid(List<CategoryServiceModel> categories) {
          return categories != null && !categories.isEmpty();
    }
}
