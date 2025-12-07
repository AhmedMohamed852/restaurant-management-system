package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.ChefDto;
import restaurant_management_system.model.Chef;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChefMapper {

      Chef toEntity(ChefDto chefDto);

      ChefDto toDto(Chef chef);

      List<ChefDto> toDtoList(List<Chef> chefList);
}
