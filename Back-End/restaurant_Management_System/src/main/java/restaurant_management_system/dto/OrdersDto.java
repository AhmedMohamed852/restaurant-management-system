package restaurant_management_system.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {


    private Long code;

    double totalPrice;

    Long totalNumber;

    @NotNull(message = "Product.Ids.Must.Not.Be.Null")
    List<OrderItemDto> orderItems;



//_______________________relation__________________

}