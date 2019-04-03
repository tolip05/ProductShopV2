package org.productshop.repositoris;

import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.ProductServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

}
