package restaurant_management_system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.enums.OrderStatus;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ORDER DTO" , description = "This is the Order DTO class contain [code ,totalPrice ,totalNumber ,status ,message ,orderItems")
public class OrdersDto {

    @Schema(description = "Order Code")
    private Long code;

    @Schema(description = "Order Total Price")
    double totalPrice;

    @Schema(description = "Order Total Number")
    Long totalNumber;

    @Schema(description = "Order Status")
    OrderStatus status;

    @Schema(description = "Order Message")
    String message;

    @Schema(description = "Order Items")
    @NotNull(message = "Product.Ids.Must.Not.Be.Null")
    List<OrderItemDto> orderItems;



//_______________________relation__________________

}