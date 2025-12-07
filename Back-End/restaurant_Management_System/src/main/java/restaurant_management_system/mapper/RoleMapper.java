package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.RoleDto;

import restaurant_management_system.model.Role;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

   Role toEntity(RoleDto roleDto);

   RoleDto toDto(Role role);

   List<RoleDto> toDtoList(List<Role> roleList);
}
