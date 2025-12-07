package restaurant_management_system.service;

import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.vm.OrdersResponseVm;

import java.util.List;

public interface OrdersService {

    OrdersDto saveOrder(OrdersDto ordersDto);

    List<OrdersDto> getAllOrdersByUserId(Long userId);

    List<OrdersDto> getAllOrders(/*int pageNumber , int pageSize*/);
}
