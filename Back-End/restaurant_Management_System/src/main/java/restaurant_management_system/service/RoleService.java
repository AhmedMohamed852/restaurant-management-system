package restaurant_management_system.service;

import restaurant_management_system.dto.RoleDto;
import restaurant_management_system.enums.RolesEnum;


public interface RoleService {

    RoleDto getRoleByCode(RolesEnum code);

}
