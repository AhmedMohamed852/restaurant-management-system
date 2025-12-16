package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.ContactInfoDto;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.mapper.ContactInfoMapper;
import restaurant_management_system.mapper.UsersMapper;
import restaurant_management_system.model.ContactInfo;
import restaurant_management_system.model.Users;
import restaurant_management_system.repo.ContactInfoRepo;
import restaurant_management_system.repo.UserRepo;
import restaurant_management_system.service.UserService;
import restaurant_management_system.service.contactInfoService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class contactInfoImpl implements contactInfoService {


    private ContactInfoRepo contactInfoRepo;
    private ContactInfoMapper contactInfoMapper;
    private UsersMapper usersMapper;
    private UserService userService;
    private UserRepo userRepo;

    @Autowired
    public contactInfoImpl(ContactInfoRepo contactInfoRepo, ContactInfoMapper contactInfoMapper ,
                           UsersMapper usersMapper , UserService userService , UserRepo userRepo) {
        this.contactInfoRepo = contactInfoRepo;
        this.contactInfoMapper = contactInfoMapper;
        this.usersMapper = usersMapper;
        this.userService = userService;
        this.userRepo = userRepo;
    }


//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO ____________________saveContactInfo___________________________
//TODO ______________________________________________________________

    @Override
    public void saveContactInfo(ContactInfoDto contactInfoDto)
    {
        if(Objects.nonNull(contactInfoDto.getId()))
        {
            throw new RuntimeException("id.Must.Not.Be.con");
        }

        UsersDto usersDto = userService.getUserById(getUserId());
        Users user = usersMapper.toEntity(usersDto);


        ContactInfo contactInfo = contactInfoMapper.toEntity(contactInfoDto);

        contactInfo.setUsers(user);


        contactInfoRepo.save(contactInfo);

    }



//TODO ____________________getAllContactInfo___________________________
//TODO ______________________________________________________________
    @Override
    public List<ContactInfoDto> getContactInfo()
    {
        List<ContactInfo> contactInfo = contactInfoRepo.findAll();

        if(contactInfo.isEmpty())
        {
            throw new RuntimeException("No.ContactInfo.Found");
        }


        List<ContactInfoDto> contactInfoDto = contactInfoMapper.toDtoList(contactInfo);

        contactInfoDto.forEach(contactInfoDto1 -> contactInfoDto1.setUser(contactInfoDto1.getUsers()));

        return contactInfoDto;
    }


//TODO ____________________getContactInfoById___________________________
//TODO ______________________________________________________________
    @Override
    public List<ContactInfoDto> getContactInfoById()
    {
        Long userId = getUserId();


        Optional<List<ContactInfo>> contactInfos = contactInfoRepo.findAllByUsersId(userId);

        if(contactInfos.isEmpty())
        {
            throw new RuntimeException("No.ContactInfo.Found");
        }
        List<ContactInfoDto> contactInfoDto = contactInfoMapper.toDtoList(contactInfos.get());

        contactInfoDto.forEach(contactInfoDto1 -> contactInfoDto1.setUser(contactInfoDto1.getUsers()));

        return contactInfoDto;
    }



//TODO ____________________getContactInfoById___________________________
//TODO ______________________________________________________________
    @Override
    public ContactInfoDto getMyContactInfoById()
    {


        Optional<ContactInfo> contactInfos = contactInfoRepo.findById(getUserId());

        if(contactInfos.isEmpty())
        {
            throw new RuntimeException("No.ContactInfo.Found");
        }

        ContactInfoDto contactInfoDto = contactInfoMapper.toDto(contactInfos.get());
        contactInfoDto.setUser(null);

        return contactInfoDto;
    }



//TODO ____________________updateReplyMessage___________________________
//TODO ______________________________________________________________

    @Override
    public void updateReplyMessage(ContactInfoDto contactInfoDto) {

        if(Objects.isNull(contactInfoDto.getId()))
        {
            throw new RuntimeException("id.Must.Not.Be.Empty");
        }


        if (contactInfoDto.getReplyMessage().isEmpty())
        {
            throw new RuntimeException("replyMessage.Must.Not.Be.Empty");
        }

        UsersDto usersDto = userService.getUserById(contactInfoDto.getUserId());
        Users user = usersMapper.toEntity(usersDto);

        ContactInfo contactInfo = contactInfoMapper.toEntity(contactInfoDto);
        contactInfo.setUsers(user);

        contactInfoRepo.save(contactInfo);
    }




//TODO _________________getUserId_____________________________
//TODO ______________________________________________________________

    public Long getUserId()
    {
        UsersDto currentUser = (UsersDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = currentUser.getId();

        return userId;
    }
}
