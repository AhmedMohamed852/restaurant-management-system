package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.CategoryDto;
import restaurant_management_system.mapper.CategoryMapper;
import restaurant_management_system.model.Category;
import restaurant_management_system.repo.CategoryRepo;
import restaurant_management_system.service.CategoryService;

import java.security.Key;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryMapper categoryMapper;

    private final CategoryRepo categoryRepo;


    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepo categoryRepo)
    {
        this.categoryMapper = categoryMapper;
        this.categoryRepo = categoryRepo;
    }



//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO _________________get All Categories___________________________
//TODO ______________________________________________________________

    @Override
    @Cacheable(value = "Categories", key = "'all'")
    public List<CategoryDto> getAllCategories()
    {

       List<Category> categories = categoryRepo.findAll();
        if(categories.isEmpty())
        {
            throw new RuntimeException("No.Categories.Found");
        }

        return categoryMapper.toDtoList(categories);
    }




//TODO _________________save Category______________________________
//TODo ____________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    public CategoryDto saveCategory(CategoryDto categoryDto)
    {
      return   helpSaveCategories(categoryDto);
    }



//TODo _________________save List of Categories___________________________
//TODo ___________________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    public List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtoList) {

     return   categoryDtoList.stream().map(this::helpSaveCategories).toList();
    }


//TODo _________________ help Save Categories ___________________________
//TODo ___________________________________________________________________

    private CategoryDto helpSaveCategories(CategoryDto categoryDto)
    {
        if(Objects.nonNull(categoryDto.getId()))
        {
            throw new RuntimeException("id.Must.Be.Null");
        }

        if(categoryRepo.findByName(categoryDto.getName()).isPresent())
        {
            throw new RuntimeException("Category.Already.Exists");
        }

        Category category = (categoryRepo.save(categoryMapper.toEntity(categoryDto)));
        categoryDto.setId(category.getId());
        return categoryDto;
    }


//TODO _________________update Category____________________________
//TODo ____________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    @CachePut(value = "Categories" , key = "'categId' + #categoryDto.id")
    public CategoryDto updateCategory(CategoryDto categoryDto)
    {
      return helpUpdateCategories(categoryDto);
    }

//TODo _________________ help Update Categories ___________________________
//TODo ___________________________________________________________________

    private CategoryDto helpUpdateCategories(CategoryDto categoryDto)
    {
        if(Objects.isNull(categoryDto.getId()))
        {
            throw new RuntimeException("id.Must.Not.Be.Null");
        }

        Optional<Category> categoryOptional = categoryRepo.findById(categoryDto.getId());
        if(categoryOptional.isEmpty())
        {
            throw new RuntimeException("Category.Not.Found");
        }

        return categoryMapper.toDto(categoryRepo.save(categoryMapper.toEntity(categoryDto)));
    }

//TODO __________________updateListOfCategories________________________
//TODo ________________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    public List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtoList)
    {
       return categoryDtoList.stream().map(this::helpUpdateCategories).toList();
    }



//TODO __________________deleteListOfCategories________________________
//TODo ________________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    public void deleteListOfCategories(List<String> namesOfCategories)
    {
        List<Category> categories = categoryRepo.findAll();

        List<String>namesExist = categories.stream().map(Category::getName).toList();

        List<String> missingNames = namesOfCategories.stream()
        .filter(name -> !namesExist.contains(name)).toList();

        if (!missingNames.isEmpty()) {
            throw new RuntimeException("Category.Not.Found");
        }

        categoryRepo.deleteAll(categories);

    }



//TODO __________________deleteCategory________________________________
//TODo ________________________________________________________________

    @Override
    @CacheEvict(value = "Categories" ,allEntries = true)
    public void deleteCategory(String categoryName)
    {
        Optional<Category> categoryOptional = categoryRepo.findByName(categoryName);
        if(categoryOptional.isEmpty())
        {
            throw new RuntimeException("Category.Not.Found");
        }

        categoryRepo.deleteById(categoryOptional.get().getId());

    }



//TODO __________________getCategoryByName_____________________________
//TODo ________________________________________________________________

    @Override
    @Cacheable(value = "Categories" ,key = "'categName' + #categoryName")
    public CategoryDto getCategoryByName(String categoryName)
    {
        Optional<Category> categoryOptional = categoryRepo.findByName(categoryName);
        if(categoryOptional.isEmpty())
        {
            throw new RuntimeException("Category.Not.Found");
        }

        return categoryMapper.toDto(categoryOptional.get());
    }
}
