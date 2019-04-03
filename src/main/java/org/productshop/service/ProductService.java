package org.productshop.service;

import org.productshop.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {
    ProductServiceModel addProduct(ProductServiceModel productServiceModel);

    List<ProductServiceModel> findAllProduct();
    ProductServiceModel findProductById(String id);
    ProductServiceModel editProduct(String id,ProductServiceModel productServiceModel);
    void deleteProduct(String id);
}
