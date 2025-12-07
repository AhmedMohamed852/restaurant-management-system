package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.service.ProductService;
import restaurant_management_system.vm.ProductsResponseVm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/api/products")
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

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAllProdByCatId/{categoryId}/{pageNumber}/{pageSize}")
    public ResponseEntity<ProductsResponseVm> getAllProductsByCategoryId(@PathVariable long categoryId , @PathVariable int pageNumber , @PathVariable int pageSize)
    {
        return ResponseEntity.ok().body(productService.getAllProductsByCategoryId( pageNumber ,pageSize,categoryId));
    }


//TODO _________________getAllProducts_______________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductsResponseVm> getAllProducts(@RequestParam int pageNumber , @RequestParam int pageSize)
    {
        return ResponseEntity.ok().body(productService.getAllProducts(pageNumber , pageSize));
    }



//TODO _________________saveProduct__________________________________
//TODO ______________________________________________________________
   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDto> saveProduct (@RequestBody @Valid ProductDto productDto ) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveProduct")).body(productService.saveProduct(productDto));
    }


//TODO _________________saveListOfProducts___________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveListOfProducts")
    public  ResponseEntity<List<ProductDto>> saveListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveListOfProducts")).body(productService.saveListOfProducts(productDtoList));
    }

//TODO _________________updateProduct________________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateProduct")
    public   ResponseEntity<ProductDto> updateProduct(@RequestBody @Valid ProductDto productDto)
    {
        return ResponseEntity.ok().body(productService.updateProduct(productDto));
    }



//TODO _________________updateListOfProducts___________________________
//TODO ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateListOfProducts")
    public ResponseEntity<List<ProductDto>> updateListOfProducts(@RequestBody @Valid List<ProductDto> productDtoList)
    {
        return ResponseEntity.ok().body(productService.updateListOfProducts(productDtoList));
    }


//TODO _________________deleteProduct__________________________________
//TODO ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Void> deleteProduct(@RequestParam String productName)
    {
        productService.deleteProduct(productName);
        return ResponseEntity.noContent().build();
    }


//TODO _________________deleteListOfProducts___________________________
//TODO ________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteListOfProducts")
    public ResponseEntity<Void> deleteListOfProducts(@RequestParam List<String> productNames)
    {
        productService.deleteListOfProducts(productNames);
        return ResponseEntity.noContent().build();
    }



//TODO _________________searchProducts_________________________________
//TODO ________________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/searchProducts")
    public ResponseEntity<ProductsResponseVm> searchProducts(@RequestParam String key , @RequestParam int pageNumber , @RequestParam int pageSize)
    {
        return ResponseEntity.ok().body(productService.searchProducts(pageNumber, pageSize , key ));
    }

//TODO _________________getProductById_________________________________
//TODO ________________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getById")
    public ResponseEntity<ProductDto> getProductById(@RequestParam Long id )
    {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }


//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________

}
