package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.CategoryDto;
import restaurant_management_system.service.CategoryService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }



//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO _________________ get All Categories ___________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>>  getAllCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }



//TODO _________________ save Category ______________________________
//TODo ____________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto categoryDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveCategory")).body(categoryService.saveCategory(categoryDto));
    }



//TODo _________________ save List of Categories ___________________________
//TODo ___________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveListOfCategories")
    public ResponseEntity<List<CategoryDto>> saveListOfCategories(@RequestBody @Valid List<CategoryDto> categoryDtoList) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveListOfCategories")).body(categoryService.saveListOfCategories(categoryDtoList));
    }


//TODO _________________ update Category ____________________________
//TODo ____________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody @Valid CategoryDto categoryDto)
    {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));

    }


//TODO __________________ updateListOfCategories ________________________
//TODo ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateListOfCategories")
    public ResponseEntity<List<CategoryDto>> updateListOfCategories (@RequestBody @Valid List<CategoryDto> categoryDto) throws URISyntaxException
    {
        return ResponseEntity.ok(categoryService.updateListOfCategories(categoryDto));
    }



//TODO __________________ deleteListOfCategories ________________________
//TODo ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteListOfCategories")
    public ResponseEntity<Void> deleteListOfCategories (@RequestParam List<String> namesOfCategories)
    {
       categoryService.deleteListOfCategories(namesOfCategories);
      return ResponseEntity.noContent().build();
    }



//TODO __________________ deleteCategory ________________________________
//TODo ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<Void> deleteCategory (@RequestParam String  nameOfCategory)
    {
        categoryService.deleteCategory(nameOfCategory);
        return ResponseEntity.noContent().build();
    }

//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________

}
