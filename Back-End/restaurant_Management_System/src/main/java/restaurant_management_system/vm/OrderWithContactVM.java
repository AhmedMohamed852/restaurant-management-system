package restaurant_management_system.vm;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.dto.OrderItemDto;
import restaurant_management_system.eNum.OrderStatus;
import restaurant_management_system.model.ContactInfo;
import restaurant_management_system.model.Orders;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderWithContactVM {

    private Long code;

    double totalPrice;

    Long totalNumber;

    OrderStatus status;

    String message;

    List<OrderItemDto> orderItems;

    private String username;

    private int age;

    private String phoneNumber;

    private String address;
}
