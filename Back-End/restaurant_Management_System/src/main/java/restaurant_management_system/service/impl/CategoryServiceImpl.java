package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.CategoryDto;
import restaurant_management_system.mapper.CategoryMapper;
import restaurant_management_system.model.Category;
import restaurant_management_system.repo.CategoryRepo;
import restaurant_management_system.service.CategoryService;

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
    public List<CategoryDto> getAllCategories()
    {

        if(categoryRepo.findAll().isEmpty())
        {
            throw new RuntimeException("No.Categories.Found");
        }

        return categoryMapper.toDtoList(categoryRepo.findAll());
    }



//TODO _________________save Category______________________________
//TODo ____________________________________________________________

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto)
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



//TODo _________________save List of Categories___________________________
//TODo ___________________________________________________________________

    @Override
    public List<CategoryDto> saveListOfCategories(List<CategoryDto> categoryDtoList) {

        List<String> names = categoryRepo.findAll().stream().map(Category::getName).toList();

      List<CategoryDto> resultValue= categoryDtoList.stream().map (categoryDto ->
        {
            if(names.contains(categoryDto.getName()))
            {
                throw new RuntimeException("Category.Already.Exists");
            }

            if(Objects.nonNull(categoryDto.getId()))
            {
                throw new RuntimeException("id.Must.Be.Null");
            }

            return  categoryMapper.toDto(categoryRepo.save(categoryMapper.toEntity(categoryDto)));

        }).toList();

        return resultValue;
    }



//TODO _________________update Category____________________________
//TODo ____________________________________________________________

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto)
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
    public List<CategoryDto> updateListOfCategories(List<CategoryDto> categoryDtoList)
    {

      List<Category>  resultValue =  categoryDtoList.stream().map(categoryDto ->{

            if(Objects.isNull(categoryDto.getId()))
            {
                throw new RuntimeException("id.Must.Not.Be.Null");
            }
            Optional<Category> categoryOptional = categoryRepo.findById(categoryDto.getId());
            if(categoryOptional.isEmpty())
            {
                throw new RuntimeException("Category.Not.Found");
            }

          return  categoryRepo.save(categoryMapper.toEntity(categoryDto));

        }).toList();

        return categoryMapper.toDtoList(categoryRepo.saveAll(resultValue));
    }



//TODO __________________deleteListOfCategories________________________
//TODo ________________________________________________________________

    @Override
    public void deleteListOfCategories(List<String> namesOfCategories)
    {
            namesOfCategories.forEach(name ->{
                Optional<Category> categoryOptional = categoryRepo.findByName(name);
                if(categoryOptional.isEmpty())
                {
                    throw new RuntimeException("Category.Not.Found");
                }

                categoryRepo.deleteById(categoryOptional.get().getId());
            });
    }



//TODO __________________deleteCategory________________________________
//TODo ________________________________________________________________

    @Override
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
