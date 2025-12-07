package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.UserDetailsDto;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.mapper.UserDetailsMapper;
import restaurant_management_system.mapper.UsersMapper;
import restaurant_management_system.model.UserDetails;
import restaurant_management_system.model.Users;
import restaurant_management_system.repo.UserDetailsRepo;
import restaurant_management_system.service.UserDetailService;
import restaurant_management_system.service.UserService;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailService {


    private UserDetailsRepo userDetailsRepo;
    private UserDetailsMapper userDetailsMapper;
    private UserService userService;
    private UsersMapper usersMapper;

    @Autowired
    public UserDetailsImpl(UserDetailsRepo userDetailsRepo, UserDetailsMapper userDetailsMapper, @Lazy UserService userService, UsersMapper usersMapper) {
        this.userDetailsRepo = userDetailsRepo;
        this.userDetailsMapper = userDetailsMapper;
        this.userService = userService;
        this.usersMapper = usersMapper;
    }


//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________addUserDetail________________________________
//TODO ______________________________________________________________
    @Override
    public void addUserDetail(UserDetailsDto userDetailsDto)
    {
        if(Objects.nonNull(userDetailsDto.getId()))
        {
            throw new RuntimeException("id.Must.Be.Null");
        }

        UserDetails userDetails = userDetailsMapper.toEntity(userDetailsDto);



        Long userId = getUserId();

        UsersDto usersDto = userService.getUserById(userId);
        Users  users = usersMapper.toEntity(usersDto);

        userDetails.setUsers(users);

        userDetailsRepo.save(userDetails);
    }




//TODO _________________getUserDetailByUserId________________________
//TODO ______________________________________________________________
    @Override
    public UserDetailsDto getUserDetailByUserId(Long userId)
    {
        if(Objects.isNull(userId))
        {
            throw new RuntimeException("Id.Must.Not.Be.Null");
        }
        Optional<UserDetails> userDetails = userDetailsRepo.findByUsers_Id(userId);
        if(userDetails.isEmpty())
        {
            throw new RuntimeException("UserDetail.Not.Found");
        }

        return userDetailsMapper.toDto(userDetails.get());
    }


//TODO _________________updateUserDetail_____________________________
//TODO ______________________________________________________________
    @Override
    public void updateUserDetail(UserDetailsDto userDetailsDto)
    {

        if(Objects.isNull(userDetailsDto.getId()))
        {
            throw new RuntimeException("id.Must.Not.Be.Null");
        }
        Optional<UserDetails> userDetails = userDetailsRepo.findById(userDetailsDto.getId());
        if(userDetails.isEmpty())
        {
            throw new RuntimeException("UserDetail.Not.Found");
        }

        userDetailsRepo.save(userDetailsMapper.toEntity(userDetailsDto));

    }

//TODO _________________deleteUserDetailByUserId_____________________________
//TODO ______________________________________________________________
    @Override
    public void deleteUserDetailByUserId(Long userId)
    {
        if (Objects.isNull(userId))
        {
            throw new RuntimeException("Id.Must.Not.Be.Null");
        }
        Optional<UserDetails> userDetails = userDetailsRepo.findByUsers_Id(userId);
        if (userDetails.isEmpty())
        {
            throw new RuntimeException("UserDetail.Not.Found");
        }
        userDetailsRepo.deleteById(userDetails.get().getId());
    }


//TODO _________________isUserHasDetails_____________________________
//TODO ______________________________________________________________
    @Override
    public boolean isUserHasDetails()
    {

        Long userId = getUserId();

        Optional<UserDetails> userDto = userDetailsRepo.findByUsers_Id(userId);

        if(userDto.isEmpty())
        {
            return false;
        }

        return true;
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
