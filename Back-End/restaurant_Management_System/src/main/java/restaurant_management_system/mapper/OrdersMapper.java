package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.OrdersDto;
import restaurant_management_system.model.Orders;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrdersMapper {

   Orders toEntity(OrdersDto orderDto);

    OrdersDto toDto(Orders order);

    List<OrdersDto> toDtoList(List<Orders> orderList);

}
