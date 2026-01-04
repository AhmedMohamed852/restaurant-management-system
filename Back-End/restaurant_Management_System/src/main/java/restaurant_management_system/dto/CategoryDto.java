package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.model.Product;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "CATEGORY DTO" , description = "This is the Category DTO class contain [name ,logo ,flag")
public class CategoryDto {


    private Long id;

    @Schema(description = "Category Name")
    @NotBlank(message = "Category.Name.NotBlank")
    String name ;

    @Schema(description = "Category Logo Path")
    String logo;

    @Schema(description = "Category Flag")
    String flag;



//____________________relation___________________________


}
