package restaurant_management_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import restaurant_management_system.dto.ContactInfoDto;
import restaurant_management_system.model.ContactInfo;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactInfoMapper {

   ContactInfo toEntity(ContactInfoDto contactInfoDto);

   ContactInfoDto toDto(ContactInfo contactInfo);

   List<ContactInfoDto> toDtoList(List<ContactInfo> contactInfoList);
   List<ContactInfo> toEntityList(List<ContactInfoDto> contactInfoList);
}
