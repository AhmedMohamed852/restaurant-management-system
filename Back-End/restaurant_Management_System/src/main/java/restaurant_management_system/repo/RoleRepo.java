package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant_management_system.eNum.RolesEnum;

import restaurant_management_system.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>
{

    Optional<Role> findByCode(RolesEnum code);

}
