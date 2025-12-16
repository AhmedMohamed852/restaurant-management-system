package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "PRODUCT DTO" , description = "This is the Product DTO class contain [name ,imagePath ,description ,categoryName ,price")
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product.Name.NotBlank")
    @Schema(description = "Product Name")
    String name ;

    @Schema(description = "Product Image Path")
    String imagePath ;

    @Schema(description = "Product Description")
    String description ;

    @Schema(description = "Product Category Name")
    String categoryName ;

    @NotNull
    @Schema(description = "Product Price")
    double price ;

//____________________relation___________________________

  //  Category category;

}
