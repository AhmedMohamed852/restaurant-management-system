package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import restaurant_management_system.dto.*;
import restaurant_management_system.eNum.OrderStatus;
import restaurant_management_system.mapper.*;

import restaurant_management_system.model.*;
import restaurant_management_system.repo.OrderItemRepo;
import restaurant_management_system.repo.OrdersRepo;
import restaurant_management_system.service.*;
import restaurant_management_system.vm.OrderWithContactVM;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrdersImpl implements OrdersService {


    private final OrderItemMapper orderItemMapper;
    private OrdersRepo ordersRepo;
    private OrdersMapper ordersMapper;
    private ProductMapper productMapper;
    private ProductService productService;
    private UserService userService;
    private UsersMapper usersMapper;
    private OrderItemRepo orderItemRepo;
   private UserDetailService userDetailsService;
   private UserDetailsMapper userDetailsMapper;

    @Autowired
    public OrdersImpl(OrdersRepo ordersRepo , OrdersMapper ordersMapper , ProductMapper productMapper
            , ProductService productService , UserService userService , UsersMapper usersMapper , OrderItemRepo orderItemRepo, OrderItemMapper orderItemMapper
    , UserDetailService userDetailsService , UserDetailsMapper userDetailsMapper )
    {
        this.ordersRepo=ordersRepo;
        this.ordersMapper=ordersMapper;
        this.productMapper=productMapper;
        this.productService=productService;
        this.userService=userService;
        this.usersMapper=usersMapper;
        this.orderItemRepo=orderItemRepo;
        this.orderItemMapper = orderItemMapper;
        this.userDetailsService=userDetailsService;
        this.userDetailsMapper=userDetailsMapper;

    }




//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO ____________________save ContactInfo ___________________________
//TODO ________________________________________________________________


    @Override
    public OrdersDto saveOrder(OrdersDto ordersDto )
    {
        //===>   Calculate Price For This Item
        ordersDto.getOrderItems().stream().forEach(orderItemDto ->
        {
              ProductDto productDto = productService.getProductById(orderItemDto.getProductId());
              double price = orderItemDto.getQuantity() * productDto.getPrice() ;
              orderItemDto.setPriceThisItem(price);
        });


        Orders orders = ordersMapper.toEntity(ordersDto);

        //===>   TotalPrice
        double totalPrice = ordersDto.getOrderItems().stream().mapToDouble(OrderItemDto::getPriceThisItem).sum();
        orders.setTotalPrice(totalPrice);

        if(orders.getTotalPrice() > 2000)
        {
            orders.setStatus(OrderStatus.APPROVAL_REQUIRED);
            orders.setMessage("APPROVAL_REQUIRED");

        }else{
            orders.setStatus(OrderStatus.CONFIRMED);
            orders.setMessage("CONFIRMED");
        }

        //===>   TotalNumber
        Long totalNumber = ordersDto.getOrderItems().stream().mapToLong(OrderItemDto::getQuantity).sum();
        orders.setTotalNumber(totalNumber);


        //===>   GetProducts
        List<Long> productIds = ordersDto.getOrderItems().stream().map( p -> p.getProductId()).toList();
        List<ProductDto> productDtoList = productService.getProductsByListOfId(productIds);
        List<Product> products = productMapper.toEntityList(productDtoList);
        orders.setProducts(products);




        //===>   GetUser
        UsersDto currentUser = (UsersDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getId();

        //===>   GetUser
        UsersDto usersDto = userService.getUserById(userId);
        Users users = usersMapper.toEntity(usersDto);
        orders.setUsers(users);

        ordersRepo.save(orders);
        orders.setCode(orders.getId());


        // ========>  Get List Of Order Item
        List<OrderItem> orderItems = ordersDto.getOrderItems().stream().map(orderItemDto ->
        {
            OrderItem item = new OrderItem();

            item.setOrder(orders);
            item.setProduct(productMapper.toEntity(productService.getProductById(orderItemDto.getProductId())));
            item.setQuantity(orderItemDto.getQuantity());
            item.setPriceThisItem(orderItemDto.getPriceThisItem());

            return item;
        }).toList();

        // ========>  SaveOrderItem
        List<OrderItem> saved = orderItemRepo.saveAll(orderItems);
        orders.setOrderItems(saved);

        // ========>  UpdateOrder
        ordersRepo.save(orders);


        OrdersDto orderResult = ordersMapper.toDto(orders);
        return orderResult;
    }



//TODO ____________________get All Orders By UserId___________________________
//TODO _______________________________________________________________________

    @Override
    public List<OrdersDto> getMyOrders()
    {

        Optional<List<Orders>> orders = ordersRepo.findByUsers_IdAndStatusAndMessageIsNotNull(getUserId() , OrderStatus.CONFIRMED);

        if(orders.isEmpty())
        {
            throw new RuntimeException("No.Orders.Found");
        }


        return ordersMapper.toDtoList(orders.get());
    }



//TODO ____________________get My Approval ___________________________________
//TODO _______________________________________________________________________

    @Override
    public List<OrdersDto> getMyApproval()
    {

        List<Orders> orders = ordersRepo.findPendingOrdersByUser(getUserId());


        if(orders.isEmpty())
        {
            throw new RuntimeException("No.Orders.Found");
        }

        return ordersMapper.toDtoList(orders);


    }
//TODO ____________________get  All Order Pending___________________________________
//TODO _______________________________________________________________________

    @Override
    public List<OrderWithContactVM> getAllOrderPending()
    {

        // get Pending Orders
        List<Orders> orders = ordersRepo.findByStatus(OrderStatus.APPROVAL_REQUIRED);

        // Chick Is Empty
        if(orders.isEmpty())
        {
            throw new RuntimeException("No.Orders.Found");
        }


        // Get User Details
        UserDetailsDto userDetailsDto = userDetailsService.getUserDetailByUserId(orders.get(0).getUsers().getId());

        // Get User By ID
        UsersDto usersDto = userService.getUserById(getUserId());

        // Convert Order To Dto
        List<OrdersDto> ordersDto = ordersMapper.toDtoList(orders);


        // Set All Data In List < orderWithContactVM >
        List<OrderWithContactVM> orderWithContactVM = ordersDto.stream()
                .map(orderDto -> {

                    OrderWithContactVM vm = new OrderWithContactVM();


                    vm.setCode(orderDto.getCode());
                    vm.setTotalPrice(orderDto.getTotalPrice());
                    vm.setTotalNumber(orderDto.getTotalNumber());
                    vm.setStatus(orderDto.getStatus());
                    vm.setMessage(orderDto.getMessage());
                    vm.setOrderItems(orderDto.getOrderItems());

                    vm.setUsername(usersDto.getUsername());
                    vm.setAge(userDetailsDto.getAge());
                    vm.setPhoneNumber(userDetailsDto.getPhoneNumber());
                    vm.setAddress(userDetailsDto.getAddress());

                    return vm;
                })
                .collect(Collectors.toList());

        return orderWithContactVM;

    }



//TODO ____________________get  All Order Pending ___________________________________
//TODO _______________________________________________________________________

 /*   @Override
    public List<OrdersDto> getAllOrderPending()
    {

        Optional<List<Orders>> orders = ordersRepo.findByStatus(OrderStatus.APPROVAL_REQUIRED);

        if(orders.isEmpty())
        {
            throw new RuntimeException("No.Orders.Found");
        }

        return ordersMapper.toDtoList(orders.get());
    }
*/


//TODO _________________ approveOrder ______________________
//TODO ______________________________________________________
    @Override
    public void approveOrder(Long orderId)
    {
        Optional<Orders> order = ordersRepo.findById(orderId);

        if(order.isEmpty())
        {
            throw new RuntimeException("Order.Not.Found");
        }

        order.get().setMessage("SUCCESS");
        order.get().setStatus(OrderStatus.CONFIRMED);

        ordersRepo.save(order.get());
    }

//TODO _________________ rejected Order ______________________
//TODO ______________________________________________________
    @Override
    public void rejectedOrder(Long orderId , String message)
    {
        Optional<Orders> order = ordersRepo.findById(orderId);

        if(order.isEmpty())
        {
            throw new RuntimeException("Order.Not.Found");
        }

        order.get().setMessage(message);
        order.get().setStatus(OrderStatus.REJECTED);

        ordersRepo.save(order.get());
    }



//TODO ____________________get All Orders____________________________________
//TODO _______________________________________________________________________
    @Override
    public List<OrdersDto> getAllOrders(/*int pageNumber , int pageSize*/)
    {
        /*validatePageNumberAndSize(pageNumber, pageSize);

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);


        Page<Orders> orders = ordersRepo.findAll(pageable);*/

        List<Orders> orders = ordersRepo.findAll();

        if(orders.isEmpty() || orders == null)
        {
            throw new RuntimeException("No.Orders.Found");
        }

        return ordersMapper.toDtoList(orders);
    }


//TODO _________________validatePageNumberAndSize______________________
//TODO ________________________________________________________________
    boolean validatePageNumberAndSize(int pageNumber, int pageSize)
    {

        if (pageNumber < 1 || pageSize <= 0)
        {
            throw new IllegalArgumentException("page.number.invalid");
        }
        return true;
    }



//TODO _________________getUserId_____________________________
//TODO _______________________________________________________

    public Long getUserId()
    {
        UsersDto currentUser = (UsersDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getId();

        return userId;
    }
}
