package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.RoleDto;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.eNum.RolesEnum;
import restaurant_management_system.mapper.RoleMapper;
import restaurant_management_system.mapper.UserDetailsMapper;
import restaurant_management_system.mapper.UsersMapper;
import restaurant_management_system.model.Users;
import restaurant_management_system.repo.UserRepo;
import restaurant_management_system.service.RoleService;
import restaurant_management_system.service.UserDetailService;
import restaurant_management_system.service.UserService;


import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    private UserRepo userRepo;
    private UsersMapper usersMapper;
    private RoleService role;
    private RoleMapper roleMapper;
    private UserDetailService userDetailService;
    private UserDetailsMapper userDetailsMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserImpl
    (UserRepo userRepo, UsersMapper usersMapper , RoleService role
        , RoleMapper roleMapper , @Lazy UserDetailService userDetailService
        , UserDetailsMapper userDetailsMapper , PasswordEncoder passwordEncoder
    )
    {
        this.userRepo = userRepo;
        this.usersMapper = usersMapper;
        this.role = role;
        this.roleMapper = roleMapper;
        this.userDetailService = userDetailService;
        this.userDetailsMapper = userDetailsMapper;
        this.passwordEncoder = passwordEncoder;
    }


//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________createUser__________________________________
//TODO ______________________________________________________________
    @Override
    public void createUser(UsersDto userDto)
    {
        if(Objects.nonNull(userDto.getId()))
        {
            throw new RuntimeException("id.Must.Be.Null");
        }

        Optional<Users> users = userRepo.findByUsername(userDto.getUsername());

        if(users.isPresent())
        {
            throw new RuntimeException("User.Already.Exists");
        }

        Users users1 = usersMapper.toEntity(userDto);
        RoleDto role1 = role.getRoleByCode(RolesEnum.USER);

        users1.setPassword(passwordEncoder.encode(users1.getPassword()));

        users1.setRoles(Arrays.asList(roleMapper.toEntity(role1)));


        userRepo.save(users1);
    }



//TODO _________________updateUser___________________________________
//TODO ______________________________________________________________
    @Override
    public void updateUser(UsersDto userDto)
    {
        if (Objects.isNull(userDto.getId()))
        {
            throw new RuntimeException("id.Must.Not.Be.Null");
        }

        Optional<Users> optionalUser = userRepo.findById(userDto.getId());
        if (optionalUser.isEmpty())
        {
            throw new RuntimeException("User.Not.Found");
        }

        userRepo.save(usersMapper.toEntity(userDto));
    }


//TODO _________________deleteUser___________________________________
//TODO ______________________________________________________________

    @Override
    public void deleteUser(Long id)
    {
        Optional<Users> User = userRepo.findById(id);

        if(User.isEmpty())
        {
            throw new RuntimeException("User.Not.Found");
        }

        userDetailService.deleteUserDetailByUserId(User.get().getId());

        userRepo.deleteById(User.get().getId());
    }


//TODO _________________getUserById__________________________________
//TODO ______________________________________________________________
    @Override
    public UsersDto getUserById(Long id)
    {
        if(Objects.isNull(id))
        {
            throw new RuntimeException("id.Must.Not.Be.Null");
        }

        Optional<Users> optionalUser = userRepo.findById(id);
        if(optionalUser.isEmpty())
        {
            throw new RuntimeException("User.Not.Found");
        }

        return usersMapper.toDto(optionalUser.get());
    }




//TODO _________________getUserByUsername____________________________
//TODO ______________________________________________________________
    @Override
    public UsersDto getUserByUsername(String username)
    {
        Optional<Users> optionalUser = userRepo.findByUsername(username);
        if(optionalUser.isEmpty())
        {
            throw new RuntimeException("User.Not.Found");
        }

        return usersMapper.toDto(optionalUser.get());
    }

}
