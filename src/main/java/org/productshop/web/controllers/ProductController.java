package org.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.productshop.domain.models.binding.ProductAddBindingModel;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.domain.models.service.ProductServiceModel;
import org.productshop.domain.models.view.ProductAllViewModel;
import org.productshop.domain.models.view.ProductDetailsViewModel;
import org.productshop.service.CategoryService;
import org.productshop.service.CloudinaryService;
import org.productshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProduct() {
        return super.view("product/add-product");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addProductConfirm(@ModelAttribute ProductAddBindingModel model,
                                          ModelAndView modelAndView) throws IOException {
        ProductServiceModel productServiceModel = this.modelMapper
                .map(model, ProductServiceModel.class);

        List<CategoryServiceModel> categoriesModels = this.categoryService.findAllCategories()
                .stream().filter(c -> model.getCategories().contains(c.getId()))
                .collect(Collectors.toList());

        productServiceModel.setCategories(categoriesModels);
        productServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(model.getImage())
        );
        this.productService.addProduct(productServiceModel);
        return super.redirect("/products/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allProducts(ModelAndView modelAndView) {

        modelAndView.addObject("products", this.productService
                .findAllProduct()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList()));
        return super.view("product/all-products", modelAndView);
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsProduct(@PathVariable String id, ModelAndView modelAndView) {
        ProductDetailsViewModel product = this.modelMapper.map(this.productService.findProductById(id),ProductDetailsViewModel.class);
        modelAndView.addObject("product", product);
        modelAndView.addObject("productId",id);
        return super.view("product/details", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView edit(@PathVariable String id,ModelAndView modelAndView){
       ProductServiceModel productServiceModel = this.productService.findProductById(id);
       ProductAddBindingModel model = this.modelMapper.map(productServiceModel,ProductAddBindingModel.class);
       model.setCategories(productServiceModel
               .getCategories().stream()
               .map(c -> c.getName()).collect(Collectors.toList()));
       modelAndView.addObject("product",model);
       modelAndView.addObject("productId",id);
       return super.view("product/edit-product",modelAndView);
    }
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    protected ModelAndView editConfirm(@PathVariable String id,
                                       @ModelAttribute ProductAddBindingModel model){
       this.productService.editProduct(id,this.modelMapper.map(model,ProductServiceModel.class));
        return super.redirect("/products/details/" + id);
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView delete(@PathVariable String id,ModelAndView modelAndView){
        ProductServiceModel productServiceModel = this.productService.findProductById(id);
        ProductAddBindingModel model = this.modelMapper.map(productServiceModel,ProductAddBindingModel.class);
        model.setCategories(productServiceModel
                .getCategories().stream()
                .map(c -> c.getName()).collect(Collectors.toList()));
        modelAndView.addObject("product",model);
        modelAndView.addObject("productId",id);
        return super.view("product/delete-product",modelAndView);
    }
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    protected ModelAndView deleteConfirm(@PathVariable String id){
        this.productService.deleteProduct(id);
        return super.redirect("/products/all");
    }


//    @GetMapping("/delete/{id}")
//    @PreAuthorize("hasRole('ROLE_MODERATOR')")
//    public ModelAndView deleteProduct(@PathVariable String id){
//        this.productService.deleteProduct(id);
//        return super.redirect("/products/all");
//    }
}
