package org.productshop.service;

import org.modelmapper.ModelMapper;
import org.productshop.domain.entities.Category;
import org.productshop.domain.entities.Product;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.error.ProductNotFoundException;
import org.productshop.repositoris.ProductRepository;
import org.productshop.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductValidationService productValidationService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductValidationService productValidationService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productValidationService = productValidationService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel addProduct(ProductServiceModel productServiceModel) {
        if (!this.productValidationService.isValid(productServiceModel)){
            throw new IllegalArgumentException();
        }
        Product product = this.modelMapper.map(productServiceModel,Product.class);
        product = this.productRepository.save(product);
        return this.modelMapper
                .map(product,ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllProduct() {

        return this.productRepository.findAll().stream()
                .map(p -> this.modelMapper.map(p,ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductById(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product with the given id not found"));
        return this.modelMapper.map(product,ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {
       Product product = this.productRepository.findById(id)
               .orElseThrow(()-> new ProductNotFoundException("Product with the given id not found"));
       productServiceModel.setCategories(
               this.categoryService.findAllCategories()
               .stream()
               .filter(c-> productServiceModel.getCategories().contains(c.getId()))
               .collect(Collectors.toList())
       );
       product.setName(productServiceModel.getName());
       product.setDescription(productServiceModel.getDescription());
       product.setPrice(productServiceModel.getPrice());
       product.setCategories(productServiceModel.getCategories().stream()
       .map(c-> this.modelMapper.map(c, Category.class)).collect(Collectors.toList()));
        return this.modelMapper.map(this.productRepository.saveAndFlush(product),ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product with the given id not found"));
        this.productRepository.delete(product);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 300000)
    private void testSchedule(){

    }
}
