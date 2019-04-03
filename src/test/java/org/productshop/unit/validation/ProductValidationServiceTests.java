package org.productshop.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.validation.ProductValidationService;
import org.productshop.validation.ProductValidationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductValidationServiceTests {
    private ProductValidationService productValidationService;

    @Before
    public void setupTest(){
        productValidationService = new ProductValidationServiceImpl();
    }
    @Test
    public void isValidWithProduct_whenNull_false(){
        Product product = null;
        boolean result = productValidationService.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValid_whenValidProduct_true(){
        Product product = new Product();
        boolean result = productValidationService
                .isValid(product);
        assertTrue(result);
    }
    @Test
    public void isValidWithProductServiceModel_whenNull_false(){
      ProductServiceModel product = null;
      boolean result = productValidationService.isValid(product);
      assertFalse(result);
    }

    @Test
    public void isValid_whenValidProductServiceModel_true(){
       ProductServiceModel product = new ProductServiceModel();
       product.setCategories(List.of(new CategoryServiceModel()));
      boolean result = productValidationService
              .isValid(product);
      assertTrue(result);
    }

    @Test
    public void invalidProduct(){
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(null);
        boolean result = productValidationService.isValid(product);
        assertFalse(result);
    }
    @Test
    public void validProduct_WithEmptyCategory(){
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        boolean result = productValidationService.isValid(product);
        assertTrue(result);
    }
}
