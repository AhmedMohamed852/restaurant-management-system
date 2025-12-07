package restaurant_management_system.service;

import restaurant_management_system.dto.UsersDto;

public interface UserService {

    void createUser(UsersDto userDto);

    UsersDto getUserByUsername(String username);

    void updateUser(UsersDto userDto);

    void deleteUser(Long id);

    UsersDto getUserById(Long id);
}
