package restaurant_management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


     String name ;

     String imagePath ;

     String description ;

     double price ;

     @CreatedDate
     private LocalDateTime createdDate ;

     @LastModifiedDate
     private LocalDateTime LastModified ;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;


//____________________relation___________________________

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany(fetch = FetchType.EAGER ,mappedBy = "products")
    List<Orders> orders;

    @OneToMany(mappedBy = "product" ,fetch = FetchType.EAGER)
    List<OrderItem> orderItems;



}
