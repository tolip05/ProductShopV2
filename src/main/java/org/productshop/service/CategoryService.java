package org.productshop.service;

import org.productshop.domain.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {
    CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel);
    List<CategoryServiceModel> findAllCategories();
    CategoryServiceModel findCategoryById(String id);
    CategoryServiceModel editCategory(String id,CategoryServiceModel categoryServiceModel);

    void deleteCategory(String id);
}
