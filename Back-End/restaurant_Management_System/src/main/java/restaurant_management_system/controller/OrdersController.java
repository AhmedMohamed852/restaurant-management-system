package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.service.OrdersService;
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



//TODO ____________________getAllOrdersByUserId______________________
//TODO ______________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getAllOrdersByUserId/{userId}")
    public ResponseEntity<List<OrdersDto>> getAllOrdersByUserId(@PathVariable Long userId)
    {
        return ResponseEntity.ok().body(ordersService.getAllOrdersByUserId(userId));
    }


//TODO ____________________getAllOrders_____________________________
//TODO ______________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrders")
    public ResponseEntity<List<OrdersDto>> getAllOrders(/*@RequestParam int pageNumber , @RequestParam int pageSize*/)
    {
        return ResponseEntity.ok().body(ordersService.getAllOrders(/*pageNumber , pageSize*/));
    }



}
