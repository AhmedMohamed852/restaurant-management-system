package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import restaurant_management_system.dto.OrderItemDto;
import restaurant_management_system.model.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

   OrderItem toEntity(OrderItemDto orderItemDto);

   OrderItemDto toDto(OrderItem orderItem);

   List<OrderItemDto> toDtoList(List<OrderItem> orderItems);

   List<OrderItem> toEntityList(List<OrderItemDto> orderItemDto);
}
