package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant_management_system.model.Chef;

import java.util.Optional;

@Repository
public interface ChefRepo extends JpaRepository<Chef, Long> {

    Optional<Chef> findByName(String name);
}
