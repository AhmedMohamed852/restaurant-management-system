package restaurant_management_system.service;

import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.vm.MyOrderHistoryVm;
import restaurant_management_system.vm.OrderWithContactVM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersService {

    OrdersDto saveOrder(OrdersDto ordersDto);

    MyOrderHistoryVm getMyOrders(int  pageNUmber , int pageSize );
    MyOrderHistoryVm getMyOrdersSorted(int  pageNUmber , int pageSize ,String typeSorted );

    MyOrderHistoryVm getMyOrdersFromDateToDateSorted(int  pageNUmber , int pageSize , LocalDate FromDate, LocalDate ToDate,String typeSorted );


    MyOrderHistoryVm getAllOrders(int pageNumber , int pageSize);
    MyOrderHistoryVm getAllOrdersSorted(int pageNumber , int pageSize , String typeSorted);


    MyOrderHistoryVm FilterAllOrdersFromDateToDate(int pageNumber , int pageSize, LocalDate FromDate, LocalDate ToDate ,String typeSorted);


    List<OrdersDto> getMyApproval();


    void approveOrder(Long orderId );

    /*List<OrderWithContactVM> approveOrderTest(Long orderId );*/

    void rejectedOrder(Long orderId ,String message );

    List<OrderWithContactVM> getAllOrderPending();


}
