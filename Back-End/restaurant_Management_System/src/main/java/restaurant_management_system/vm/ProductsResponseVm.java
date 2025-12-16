package restaurant_management_system.vm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.dto.ProductDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PRODUCTS RESPONSE VM" , description = "This is the Products Response VM class contain [products ,totalProducts]")
public class ProductsResponseVm {

    @Schema(name = "PRODUCTS" , description = "List Of Products")
    private List<ProductDto> products;

    @Schema(name = "TOTAL PRODUCTS" , description = "Total Products Count")
    private Long totalProducts;
}
