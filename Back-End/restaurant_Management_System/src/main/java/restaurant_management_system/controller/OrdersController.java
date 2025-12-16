package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.service.OrdersService;
import restaurant_management_system.vm.OrderWithContactVM;
import restaurant_management_system.vm.OrdersResponseVm;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/orders/")
public class OrdersController {

    private OrdersService ordersService;

    public OrdersController(OrdersService ordersService)
    {
        this.ordersService = ordersService;
    }

//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO ____________________createOrder______________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @PostMapping("submit")
    public ResponseEntity<OrdersDto> createOrder(@RequestBody @Valid OrdersDto ordersDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("submit")).body(ordersService.saveOrder(ordersDto));
    }



//TODO ____________________ get My Orders History ______________________
//TODO ______________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getMyOrders")
    public ResponseEntity<List<OrdersDto>> getMyOrders()
    {
        return ResponseEntity.ok().body(ordersService.getMyOrders());
    }


//TODO ____________________ get My Approval  ______________________
//TODO ____________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getMyApproval")
    public ResponseEntity<List<OrdersDto>> getAllOrderPending()
    {
        return ResponseEntity.ok().body(ordersService.getMyApproval());
    }


//TODO ____________________ Approve Orders  ______________________
//TODO ___________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("approveOrder/{orderId}")
    public ResponseEntity<List<OrdersDto>> approveOrder(@PathVariable Long orderId)
    {
        ordersService.approveOrder(orderId );
        return ResponseEntity.noContent().build();
    }


//TODO ____________________ Rejected Orders  ______________________
//TODO ___________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("rejectedOrder/{orderId}/{message}")
    public ResponseEntity<List<OrdersDto>> rejectedOrder(@PathVariable Long orderId ,@PathVariable String message)
    {
        ordersService.rejectedOrder(orderId , message);
        return ResponseEntity.noContent().build();
    }



//TODO ____________________get All Orders Pending_____________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrdersPending")
    public ResponseEntity<List<OrderWithContactVM>> getAllOrdersPending()
    {
        return ResponseEntity.ok().body(ordersService.getAllOrderPending());
    }



//TODO ____________________getAllOrders_____________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrders")
    public ResponseEntity<List<OrdersDto>> getAllOrders()
    {
        return ResponseEntity.ok().body(ordersService.getAllOrders(/*pageNumber , pageSize*/));
    }

//TODO _______________________ FINISH _________________________________________



}
