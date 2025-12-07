package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.model.Product;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

   Product toEntity(ProductDto productDto);


   ProductDto toDto(Product product);

   List<ProductDto> toDtoList(List<Product> productList);

   List<Product> toEntityList(List<ProductDto> productDtoList);
}
