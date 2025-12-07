package restaurant_management_system.repo;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import restaurant_management_system.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Page<Product> findAllByCategoryIdOrderById(Long categoryId , Pageable pageable);


    List<Product> findAllByOrderByIdAsc();
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.name IN :names")
    void deleteAllByNameIn(@Param("names") List<String> names);

    List<Product> findAllByNameIn(List<String> names);

    @Query(value = "SELECT * FROM product WHERE LOWER(name) LIKE LOWER(:key || '%') " +
            "OR LOWER(description) LIKE LOWER(:key || '%')", nativeQuery = true)
   Page<Product> searchProducts(@Param("key") String key ,Pageable pageable);




}
