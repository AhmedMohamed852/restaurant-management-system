package restaurant_management_system.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant_management_system.enums.OrderStatus;
import restaurant_management_system.model.Orders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    Optional<List<Orders>> findByUsers_Id(Long userId);

    Optional<List<Orders>> findByUsers_IdAndStatusAndMessageIsNull(Long userId, OrderStatus status);

    Page<Orders> findByUsers_IdAndStatusAndMessageIsNotNull(Long userId, OrderStatus status, Pageable pageable);

    Page<Orders> findByUsers_IdAndStatusOrderByCreatedDateAsc(Long userId, OrderStatus status, Pageable pageable);

    Page<Orders> findByUsers_IdAndStatusAndCreatedDateBetweenOrderByCreatedDateAsc(
            Long userId, OrderStatus status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    Page<Orders> findByUsers_IdAndStatusAndCreatedDateBetweenOrderByCreatedDateDesc(
            Long userId, OrderStatus status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    Page<Orders> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
    Page<Orders> findByCreatedDateBetweenOrderByCreatedDateAsc(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    Page<Orders> findByUsers_IdAndStatusOrderByCreatedDateDesc(Long userId, OrderStatus status, Pageable pageable);

    Page<Orders> findAllByOrderByCreatedDateAsc(Pageable pageable);

    Page<Orders> findAllByOrderByCreatedDateDesc(Pageable pageable);




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
