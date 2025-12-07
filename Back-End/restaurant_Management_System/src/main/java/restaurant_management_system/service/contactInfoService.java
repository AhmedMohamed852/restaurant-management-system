package restaurant_management_system.service;

import restaurant_management_system.dto.ContactInfoDto;

import java.util.List;

public interface contactInfoService {

    void saveContactInfo(ContactInfoDto contactInfoDto);

    List<ContactInfoDto> getContactInfo();

    List<ContactInfoDto> getContactInfoById();

    void updateReplyMessage(ContactInfoDto contactInfoDto);
}
