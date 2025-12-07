package restaurant_management_system.service;

import restaurant_management_system.dto.UserDetailsDto;

public interface UserDetailService {

    void addUserDetail(UserDetailsDto userDetailsDto);

    UserDetailsDto getUserDetailByUserId(Long userId);

    void updateUserDetail(UserDetailsDto userDetailsDto);

    void deleteUserDetailByUserId(Long userId);

    boolean isUserHasDetails();
}
