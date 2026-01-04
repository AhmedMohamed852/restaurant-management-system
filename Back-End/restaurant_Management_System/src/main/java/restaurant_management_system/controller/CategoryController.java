package restaurant_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.CategoryDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.CategoryService;
import restaurant_management_system.vm.ProductsResponseVm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/categories")
@Tag( name = "Category Controller"  , description = "API For Manage Categories" )

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

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Categories",
            description = "API Get All Categories" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Categories Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>>  getAllCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }



//TODO _________________ save Category ______________________________
//TODo ____________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save Category",
            description = "API To Save Category",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryDto categoryDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveCategory")).body(categoryService.saveCategory(categoryDto));
    }



//TODo _________________ save List of Categories ___________________________
//TODo ___________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save List Of Categories",
            description = "API To Save List Of Categories",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveListOfCategories")
    public ResponseEntity<List<CategoryDto>> saveListOfCategories(@RequestBody @Valid List<CategoryDto> categoryDtoList) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveListOfCategories")).body(categoryService.saveListOfCategories(categoryDtoList));
    }


//TODO _________________ update Category ____________________________
//TODo ____________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Update Category",
            description = "API To Update Category",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateCategory")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody @Valid CategoryDto categoryDto)
    {
        return ResponseEntity.ok(categoryService.updateCategory(categoryDto));

    }


//TODO __________________ updateListOfCategories ________________________
//TODo ________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Update List Of Categories",
            description = "API To Update List Of Categories",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = CategoryDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Not Found"  ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateListOfCategories")
    public ResponseEntity<List<CategoryDto>> updateListOfCategories (@RequestBody @Valid List<CategoryDto> categoryDto) throws URISyntaxException
    {
        return ResponseEntity.ok(categoryService.updateListOfCategories(categoryDto));
    }



//TODO __________________ deleteListOfCategories ________________________
//TODo ________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Delete List Of Categories",
            description = "API To Delete List Of Categories",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteListOfCategories")
    public ResponseEntity<Void> deleteListOfCategories (@RequestParam List<String> namesOfCategories)
    {
       categoryService.deleteListOfCategories(namesOfCategories);
      return ResponseEntity.noContent().build();
    }



//TODO __________________ deleteCategory ________________________________
//TODo ________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Delete  Categories",
            description = "API To Delete Category",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Category Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
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
