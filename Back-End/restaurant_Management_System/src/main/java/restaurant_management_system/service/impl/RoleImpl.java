package restaurant_management_system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant_management_system.dto.RoleDto;
import restaurant_management_system.eNum.RolesEnum;
import restaurant_management_system.mapper.RoleMapper;
import restaurant_management_system.repo.RoleRepo;
import restaurant_management_system.service.RoleService;

import java.util.Objects;

@Service
public class RoleImpl implements RoleService {


    private RoleRepo roleRepo;

    private RoleMapper roleMapper;

    @Autowired
    public RoleImpl(RoleRepo roleRepo, RoleMapper roleMapper)
    {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }


//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________getRoleByCode________________________________
//TODO ______________________________________________________________

    @Override
    public RoleDto getRoleByCode(RolesEnum code)
    {
        if(Objects.isNull(code))
        {
            throw new RuntimeException("code.Must.Not.Be.Null");
        }

        return roleMapper.toDto(roleRepo.findByCode(code).get());

    }
}
