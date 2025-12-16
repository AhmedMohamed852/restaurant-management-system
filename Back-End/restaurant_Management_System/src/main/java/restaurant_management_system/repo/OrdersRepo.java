package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant_management_system.eNum.OrderStatus;
import restaurant_management_system.model.Orders;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    Optional<List<Orders>> findByUsers_Id(Long userId);

    Optional<List<Orders>> findByUsers_IdAndStatusAndMessageIsNull(Long userId, OrderStatus status);
    Optional<List<Orders>> findByUsers_IdAndStatusAndMessageIsNotNull(Long userId, OrderStatus status);

    @Query(
            value = "SELECT * FROM ORDERS o " +
                    "WHERE o.USERS_ID = :userId " +
                    "AND o.MESSAGE != 'CONFIRMED' " +
                    "AND o.STATUS = 'CONFIRMED' OR O.STATUS = 'APPROVAL_REQUIRED' OR O.STATUS = 'REJECTED'" ,
            nativeQuery = true
    )
    List<Orders> findPendingOrdersByUser(@Param("userId") Long userId);

    List<Orders> findByStatus(OrderStatus orderStatus);
}
