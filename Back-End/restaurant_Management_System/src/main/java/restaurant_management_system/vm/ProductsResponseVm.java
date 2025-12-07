package restaurant_management_system.vm;

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
public class ProductsResponseVm {

    private List<ProductDto> products;
    private Long totalProducts;
}
