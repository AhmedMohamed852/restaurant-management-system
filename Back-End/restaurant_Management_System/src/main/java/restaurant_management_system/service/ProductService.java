package restaurant_management_system.service;

import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.vm.ProductsResponseVm;

import java.util.List;

public interface ProductService
{

    ProductsResponseVm getAllProductsByCategoryId(int pageNumber , int pageSize , Long categoryId);

    ProductDto saveProduct(ProductDto productDto );

    List<ProductDto> saveListOfProducts(List<ProductDto> productDtoList);

    ProductDto updateProduct(ProductDto productDto);

    List<ProductDto> updateListOfProducts(List<ProductDto> productDtoList);

    void deleteProduct(String productName);

    void deleteListOfProducts(List<String> namesOfProducts);

    ProductsResponseVm searchProducts(int pageNumber , int pageSize , String key);

    ProductsResponseVm getAllProducts(int pageNumber , int pageSize);

    List<ProductDto> getProductsByListOfId(List<Long> ids);

    ProductDto getProductById(Long id);


}
