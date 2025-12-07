package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CategoryDto {


    private Long id;

    @NotBlank(message = "Category.Name.NotBlank")
    String name ;

    String logo;

    String flag;



//____________________relation___________________________

  //  List<Product> products;

/*
    {
        "id": 1,
            "name": "Pizzas",
            "logo": "images/pizzas.png",
            "flag": true
    }
*/


}
