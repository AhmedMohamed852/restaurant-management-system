package restaurant_management_system.service;

import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.vm.LoginRequestVm;
import restaurant_management_system.vm.LoginResponseVm;

public interface AuthService {

    void signUp(UsersDto userDto);

    LoginResponseVm login(LoginRequestVm loginRequestVm);
}
