package restaurant_management_system.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import restaurant_management_system.dto.OrdersDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponseVm {

    private List<OrdersDto> orders;
    private Long totalOrders;


}
