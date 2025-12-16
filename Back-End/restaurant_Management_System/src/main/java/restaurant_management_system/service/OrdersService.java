package restaurant_management_system.service;

import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.vm.OrderWithContactVM;
import restaurant_management_system.vm.OrdersResponseVm;

import java.util.List;

public interface OrdersService {

    OrdersDto saveOrder(OrdersDto ordersDto);

    List<OrdersDto> getMyOrders();

    List<OrdersDto> getAllOrders(/*int pageNumber , int pageSize*/);

    List<OrdersDto> getMyApproval();


    void approveOrder(Long orderId );

    /*List<OrderWithContactVM> approveOrderTest(Long orderId );*/

    void rejectedOrder(Long orderId ,String message );

    List<OrderWithContactVM> getAllOrderPending();
}
