package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant_management_system.model.UserDetails;

import java.util.Optional;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUsers_Id(Long id);
}
