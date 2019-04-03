package org.productshop.web.controllers;

import org.modelmapper.ModelMapper;
import org.productshop.domain.models.binding.CategoryAddBindingModel;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.domain.models.view.CategoryViewModel;
import org.productshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController{

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(){
        return super.view("/category/add-category");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@ModelAttribute CategoryAddBindingModel model){
        this.categoryService.addCategory(this.modelMapper.map(model, CategoryServiceModel.class));
        return super.redirect("/categories/all");
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView allCategories(ModelAndView modelAndView){
        List<CategoryViewModel>categories = this.categoryService.findAllCategories()
                .stream()
                .map(c -> this.modelMapper.map(c,CategoryViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("categories",categories);
        return super.view("category/all-categories",modelAndView);
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategory(@PathVariable String id,ModelAndView modelAndView){
        CategoryServiceModel category = this.categoryService
                .findCategoryById(id);
        CategoryViewModel viewModel = this.modelMapper.map(category, CategoryViewModel.class);
        modelAndView.addObject("model"
                ,viewModel);
        return super.view("category/edit-category",modelAndView);
    }
    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editConfirm(@PathVariable String id,@ModelAttribute CategoryAddBindingModel model){
       this.categoryService.editCategory(id,this.modelMapper.map(model,CategoryServiceModel.class));
        return super.redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategory(@PathVariable String id,ModelAndView modelAndView){
        CategoryServiceModel category = this.categoryService
                .findCategoryById(id);
        CategoryViewModel viewModel = this.modelMapper.map(category, CategoryViewModel.class);
        modelAndView.addObject("model"
                ,viewModel);
        return super.view("category/delete-category",modelAndView);
    }
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteConfirm(@PathVariable String id){
        this.categoryService.deleteCategory(id);
        return super.redirect("/categories/all");
    }
    @GetMapping("fetch")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @ResponseBody
    public List<CategoryViewModel> fetchCategories(){
        return this.categoryService
                .findAllCategories()
                .stream()
                .map(c -> this.modelMapper.map(c,CategoryViewModel.class))
                .collect(Collectors.toList());
    }


}
