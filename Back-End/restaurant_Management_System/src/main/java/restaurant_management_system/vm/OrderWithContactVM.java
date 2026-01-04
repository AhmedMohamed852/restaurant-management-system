package restaurant_management_system.vm;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.dto.OrderItemDto;
import restaurant_management_system.enums.OrderStatus;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ORDER WITH CONTACT VM" , description = "This is the Order With Contact VM class contain [code ,totalPrice ,totalNumber ,status ,message ,orderItems ,username ,age ,phoneNumber ,address]")
public class OrderWithContactVM {

    @Schema(name = "ORDER CODE" , description = "Order Code")
    private Long code;

    @Schema(description = "Total Order Price")
    double totalPrice;

    @Schema(description = "Total Order Item")
    Long totalNumber;

    @Schema(description = "Order Status")
    OrderStatus status;

    @Schema(description = "Order Message")
    String message;

    @Schema(description = "Order Items")
    List<OrderItemDto> orderItems;

    @Schema(description = "Customer Name")
    private String username;

    @Schema(description = "Customer Age")
    private int age;

    @Schema(description = "Customer Phone Number")
    private String phoneNumber;

    @Schema(description = "Customer Address")
    private String address;
}
