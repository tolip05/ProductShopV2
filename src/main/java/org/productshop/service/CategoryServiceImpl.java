package org.productshop.service;

import org.modelmapper.ModelMapper;
import org.productshop.domain.entities.Category;
import org.productshop.domain.models.service.CategoryServiceModel;
import org.productshop.repositoris.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel) {
       Category category = this.modelMapper.map(categoryServiceModel,Category.class);
        return this.modelMapper.map(this.categoryRepository.saveAndFlush(category),CategoryServiceModel.class);
    }

    @Override
    public List<CategoryServiceModel> findAllCategories() {

        return this.categoryRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c,CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryServiceModel findCategoryById(String id) {
        return this.modelMapper
                .map(this.categoryRepository.getOne(id),CategoryServiceModel.class);
    }

    @Override
    public CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel) {
       Category category = this.categoryRepository
               .findById(id).orElseThrow(()-> new IllegalArgumentException());
       category.setName(categoryServiceModel.getName());
       return this.modelMapper.map(this.categoryRepository.saveAndFlush(category),CategoryServiceModel.class);
    }

    @Override
    public void deleteCategory(String id) {
        this.categoryRepository.deleteById(id);
    }
}
