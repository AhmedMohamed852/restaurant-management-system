package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.model.Users;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper {

   Users toEntity(UsersDto usersDto);

   UsersDto toDto(Users users);

   List<UsersDto> toDtoList(List<Users> usersList);
}
