package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import restaurant_management_system.config.tokenHandler.TokenHandler;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.mapper.UsersMapper;
import restaurant_management_system.repo.RoleRepo;
import restaurant_management_system.service.AuthService;
import restaurant_management_system.service.UserService;
import restaurant_management_system.vm.LoginRequestVm;
import restaurant_management_system.vm.LoginResponseVm;

@Service
public class AuthImpl implements AuthService {

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private TokenHandler tokenHandler;
    private RoleRepo roleRepo;
    private UsersMapper usersMapper;

    @Autowired
    public AuthImpl(UserService userService, PasswordEncoder passwordEncoder, TokenHandler tokenHandler , RoleRepo roleRepo ,
                    UsersMapper usersMapper) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenHandler = tokenHandler;
        this.roleRepo = roleRepo;
        this.usersMapper = usersMapper;
    }






//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO ____________________userSignUp_________________________________
//TODO _______________________________________________________________
    @Override
    //@Cacheable(value = "authsignUp" , key = "'usignUp' + userDto.id")
    public void signUp(UsersDto userDto)
    {
        userService.createUser(userDto);
    }


//TODO ____________________userLogin_________________________________
//TODO _______________________________________________________________
    @Override
    //@Cacheable(value = "authLogin" , key = "'uLogin' + userDto.id")
    public LoginResponseVm login(LoginRequestVm loginRequestVm)
    {
        UsersDto usersDto = userService.getUserByUsername(loginRequestVm.getUsername());

        if(!passwordEncoder.matches(loginRequestVm.getPassword(),usersDto.getPassword()))
        {
            throw new RuntimeException("wrong.password");
        }

      // List<Role> usersDto1 =  roleRepo.findByUsers(usersMapper.toEntity(usersDto));


        return new LoginResponseVm(tokenHandler.createToken(usersDto) ,usersDto.getId());
    }
}
