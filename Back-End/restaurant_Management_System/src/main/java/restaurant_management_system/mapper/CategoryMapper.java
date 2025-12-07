package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.CategoryDto;
import restaurant_management_system.model.Category;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

   Category toEntity(CategoryDto categoryDto);

   CategoryDto toDto(Category category);

   List<CategoryDto> toDtoList(List<Category> categoryList);
}
