package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant_management_system.model.Orders;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    Optional<List<Orders>> findByUsers_Id(Long userId);

}
