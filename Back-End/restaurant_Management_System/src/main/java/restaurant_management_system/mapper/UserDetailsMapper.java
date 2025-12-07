package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.UserDetailsDto;
import restaurant_management_system.model.UserDetails;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDetailsMapper {

   UserDetails toEntity(UserDetailsDto userDetails);

   UserDetailsDto toDto(UserDetails userDetails);

   List<UserDetailsDto> toDtoList(List<UserDetails> userDetailsList);
}
