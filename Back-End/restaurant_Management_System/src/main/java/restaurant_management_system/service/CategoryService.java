package restaurant_management_system.service;

import restaurant_management_system.dto.CategoryDto;

import java.util.List;

public interface CategoryService
{

    List<CategoryDto> getAllCategories();

    CategoryDto saveCategory(CategoryDto categoryDto);

    List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtoList);

    CategoryDto updateCategory(CategoryDto categoryDto);

    List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtoList);

    void deleteListOfCategories(List<String> namesOfCategories);

    void deleteCategory(String categoryName);

    CategoryDto getCategoryByName(String categoryName);






}
