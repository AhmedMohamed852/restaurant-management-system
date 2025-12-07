package restaurant_management_system.service.impl;

import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.OrderItemDto;
import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.dto.ProductDto;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.mapper.OrderItemMapper;
import restaurant_management_system.mapper.OrdersMapper;
import restaurant_management_system.mapper.ProductMapper;

import restaurant_management_system.mapper.UsersMapper;
import restaurant_management_system.model.OrderItem;
import restaurant_management_system.model.Orders;
import restaurant_management_system.model.Product;
import restaurant_management_system.model.Users;
import restaurant_management_system.repo.OrderItemRepo;
import restaurant_management_system.repo.OrdersRepo;
import restaurant_management_system.service.OrdersService;
import restaurant_management_system.service.ProductService;
import restaurant_management_system.service.UserService;
import restaurant_management_system.vm.OrdersResponseVm;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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

    @Autowired
    public OrdersImpl(OrdersRepo ordersRepo , OrdersMapper ordersMapper , ProductMapper productMapper
            , ProductService productService , UserService userService , UsersMapper usersMapper , OrderItemRepo orderItemRepo, OrderItemMapper orderItemMapper)
    {
        this.ordersRepo=ordersRepo;
        this.ordersMapper=ordersMapper;
        this.productMapper=productMapper;
        this.productService=productService;
        this.userService=userService;
        this.usersMapper=usersMapper;
        this.orderItemRepo=orderItemRepo;
        this.orderItemMapper = orderItemMapper;
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
    public List<OrdersDto> getAllOrdersByUserId(Long userId)
    {
        if(Objects.isNull(userId))
        {
            throw new RuntimeException("Id.Must.Not.Be.Null");
        }

        Optional<List<Orders>> orders = ordersRepo.findByUsers_Id(userId);

        if(orders.isEmpty())
        {
            throw new RuntimeException("No.Orders.Found");
        }

        return ordersMapper.toDtoList(orders.get());
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
}
