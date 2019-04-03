package org.productshop.integration.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.repositoris.ProductRepository;
import org.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {
    @Autowired
    private ProductService service;
    @MockBean
    private ProductRepository mockRepository;

    @Test
    public void createProduct_whenValid_productCreated() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        when(mockRepository.save(any()))
                .thenReturn(new Product());
    service.addProduct(product);
    verify(mockRepository)
            .save(any());
    }

    @Test(expected = Exception.class)
    public void createProduct_whenNull_throw() {
        service.addProduct(null);
        verify(mockRepository)
                .save(any());
    }
}
