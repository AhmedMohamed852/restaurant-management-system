package restaurant_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.OrdersService;
import restaurant_management_system.vm.MyOrderHistoryVm;
import restaurant_management_system.vm.OrderWithContactVM;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/orders/")
@Tag(name = "Orders Controller", description = "API For Manage Orders")
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
// TODO -----------> SWAGGER {
@Operation(
        summary = "Create Order",
        description = "API To Create Order",

        responses = {
                @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                        content = @Content(schema = @Schema(implementation = OrdersDto.class))
                )
        }

)
// TODO               SWAGGER }     <---------------------

@PreAuthorize("hasAnyRole('USER' ,'ADMIN')")

    @PostMapping("submit")
    public ResponseEntity<OrdersDto> createOrder(@RequestBody @Valid OrdersDto ordersDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("submit")).body(ordersService.saveOrder(ordersDto));
    }



//TODO ____________________ get My Orders History ______________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get My Orders History",
            description = "API To Get My Orders History",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getMyOrders/{pageNUmber}/{pageSize}")
    public ResponseEntity<MyOrderHistoryVm> getMyOrders(@PathVariable int pageNUmber ,@PathVariable int pageSize )
    {
        return ResponseEntity.ok().body(ordersService.getMyOrders(pageNUmber , pageSize));
    }




//TODO ____________________ Filter My Orders History From Date To Date  _____________
//TODO _______________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Filter My Orders History From Date To Date",
            description = "API To Filter My Orders History From Date To Date ",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("filterMyOrdersFromDateToDate/{pageNUmber}/{pageSize}/{FromDate}/{ToDate}/{typeSorted}")
    public ResponseEntity<MyOrderHistoryVm> filterMyOrdersFromDateToDate(@PathVariable int pageNUmber , @PathVariable int pageSize , @PathVariable LocalDate FromDate, @PathVariable LocalDate ToDate, @PathVariable String typeSorted )
    {
        return ResponseEntity.ok().body(ordersService.getMyOrdersFromDateToDateSorted(pageNUmber , pageSize , FromDate , ToDate ,typeSorted));
    }



//TODO ____________________get My Orders Sorted ___________________________
//TODO _______________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get My Orders History Sorted",
            description = "API To Get My Orders History",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getMyOrdersSorted/{pageNUmber}/{pageSize}/{typeSorted}")
    public ResponseEntity<MyOrderHistoryVm> getMyOrdersSorted(@PathVariable int pageNUmber ,@PathVariable int pageSize , @PathVariable String typeSorted )
    {
        return ResponseEntity.ok().body(ordersService.getMyOrdersSorted(pageNUmber , pageSize , typeSorted ));
    }


//TODO ____________________ get My Approval  ______________________
//TODO ____________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get My Approval",
            description = "API To Get My Approval",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("getMyApproval")
    public ResponseEntity<List<OrdersDto>> getAllOrderPending()
    {
        return ResponseEntity.ok().body(ordersService.getMyApproval());
    }


//TODO ____________________ Approve Orders  ______________________
//TODO ___________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Approve Order",
            description = "API To Approve Order",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("approveOrder/{orderId}")
    public ResponseEntity<List<OrdersDto>> approveOrder(@PathVariable Long orderId)
    {
        ordersService.approveOrder(orderId );
        return ResponseEntity.noContent().build();
    }


//TODO ____________________ Rejected Orders  ______________________
//TODO ___________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Rejected Order",
            description = "API To Rejected Order" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("rejectedOrder/{orderId}/{message}")
    public ResponseEntity<List<OrdersDto>> rejectedOrder(@PathVariable Long orderId ,@PathVariable String message)
    {
        ordersService.rejectedOrder(orderId , message);
        return ResponseEntity.noContent().build();
    }



//TODO ____________________get All Orders Pending_____________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Orders Pending",
            description = "API To Get All Orders Pending",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrderWithContactVM.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrdersPending")
    public ResponseEntity<List<OrderWithContactVM>> getAllOrdersPending()
    {
        return ResponseEntity.ok().body(ordersService.getAllOrderPending());
    }



//TODO ____________________get All Orders Sorted_____________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Orders Sorted",
            description = "API To Get All Orders Sorted" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrdersSorted/{pageNumber}/{pageSize}/{typeSorted}")
    public ResponseEntity<MyOrderHistoryVm> getAllOrdersSorted(@PathVariable int pageNumber ,@PathVariable int pageSize ,@PathVariable String typeSorted )
    {
        return ResponseEntity.ok().body(ordersService.getAllOrdersSorted(pageNumber , pageSize ,typeSorted));
    }


//TODO ____________________get All Orders _____________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Orders",
            description = "API To Get All Orders" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getAllOrders/{pageNumber}/{pageSize}")
    public ResponseEntity<MyOrderHistoryVm> getAllOrders(@PathVariable int pageNumber ,@PathVariable int pageSize )
    {
        return ResponseEntity.ok().body(ordersService.getAllOrders(pageNumber , pageSize));
    }


//TODO ____________________Filter All Orders From Date To Date ______________
//TODO _______________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Orders From Date To Date",
            description = "API To Get All Orders From Date To Date" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = OrdersDto.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status No Orders Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("FilterAllOrdersFromDateToDate/{pageNumber}/{pageSize}/{FromDate}/{ToDate}/{typeSorted}")
    public ResponseEntity<MyOrderHistoryVm> FilterAllOrdersFromDateToDate(@PathVariable int pageNumber ,@PathVariable int pageSize , @PathVariable LocalDate FromDate, @PathVariable LocalDate ToDate  ,@PathVariable String typeSorted)
    {
        return ResponseEntity.ok().body(ordersService.FilterAllOrdersFromDateToDate(pageNumber , pageSize ,FromDate , ToDate ,typeSorted));
    }

//TODO _______________________ FINISH _________________________________________



}
