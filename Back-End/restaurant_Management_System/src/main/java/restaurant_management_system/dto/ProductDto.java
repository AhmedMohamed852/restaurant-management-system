package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.model.Category;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    private Long id;
    @NotBlank(message = "Product.Name.NotBlank")
    String name ;

    String imagePath ;

    String description ;

    String categoryName ;

    @NotNull
    double price ;

//____________________relation___________________________

  //  Category category;

}
