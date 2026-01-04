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
import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.ProductService;
import restaurant_management_system.vm.ProductsResponseVm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/products")
@Tag( name = "Product Controller" , description = "API For Manage Products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________getAllProductsByCategoryId___________________
//TODO ______________________________________________________________

// TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Products By Category Id",
            description = "Get All Products Py Category Id" ,

            responses = {
                @ApiResponse( responseCode = "200", description = "Http Status Success" ,
                              content = @Content(schema = @Schema(implementation = ProductsResponseVm.class))
                            ) ,

                @ApiResponse(responseCode = "500" ,description = "Http Status Products Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                            )

                        }
        )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAllProdByCatId/{categoryId}/{pageNumber}/{pageSize}")
    public ResponseEntity<ProductsResponseVm> getAllProductsByCategoryId(@PathVariable long categoryId , @PathVariable int pageNumber , @PathVariable int pageSize)
    {
        return ResponseEntity.ok().body(productService.getAllProductsByCategoryId( pageNumber ,pageSize,categoryId));
    }



//TODO _________________getAllProducts_______________________________
//TODO ______________________________________________________________

// TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Products" ,
            description = "Get All Products" ,

            responses = {
                        @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                                      content = @Content(schema = @Schema(implementation = ProductsResponseVm.class))
                                      ) ,

                        @ApiResponse( responseCode = "500" ,description = "Http Status Products Not Found" ,
                                      content = @Content(schema = @Schema(implementation = MessageResponse.class))
                                     )
                       }

         )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductsResponseVm> getAllProducts(@RequestParam int pageNumber , @RequestParam int pageSize)
    {
        return ResponseEntity.ok().body(productService.getAllProducts(pageNumber , pageSize));
    }



//TODO _________________ save Product __________________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save Product",
            description = "API To Save Product",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDto> saveProduct (@RequestBody @Valid ProductDto productDto ) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveProduct")).body(productService.saveProduct(productDto));
    }



//TODO _________________ saveListOfProducts ___________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save List Of Products",
            description = "API To Save List Of Products",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveListOfProducts")
    public  ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveListOfProducts")).body(productService.saveListOfProducts(productDtoList));
    }



//TODO _________________ update Product ________________________________
//TODO ______________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Update Product",
            description = "API To Update Product",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateProduct")
    public   ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto)
    {
        return ResponseEntity.ok().body(productService.updateProduct(productDto));
    }



//TODO _________________ update List Of Products ___________________________
//TODO ________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Update List Of Products",
            description = "API To Update List Of Products" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)
    {
        return ResponseEntity.ok().body(productService.updateListOfProducts(productDtoList));
    }


//TODO _________________ deleteProduct __________________________________
//TODO ________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Delete Product" ,
            description = "API To Delete Product",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ),

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestParam String productName)
    {
        productService.deleteProduct(productName);
        return ResponseEntity.noContent().build();
    }


//TODO _________________ deleteListOfProducts ___________________________
//TODO ________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Delete List Of Products",
            description = "API To Delete List Of Products",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteListOfProducts")
    public ResponseEntity<Void> deleteListOfProducts(@RequestParam List<String> productNames)
    {
        productService.deleteListOfProducts(productNames);
        return ResponseEntity.noContent().build();
    }



//TODO _________________ searchProducts _________________________________
//TODO ________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Search Products",
            description = "API To Search Products By Key Word",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductsResponseVm.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/searchProducts")
    public ResponseEntity<ProductsResponseVm> searchProducts(@RequestParam String key , @RequestParam int pageNumber , @RequestParam int pageSize)
    {
        return ResponseEntity.ok().body(productService.searchProducts(pageNumber, pageSize , key ));
    }

//TODO _________________getProductById_________________________________
//TODO ________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get Product By Id" ,
            description  = "API To Get Product By Id",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Product Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getById")
    public ResponseEntity<ProductDto> getProductById(@RequestParam Long id )
    {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }


//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________

}
