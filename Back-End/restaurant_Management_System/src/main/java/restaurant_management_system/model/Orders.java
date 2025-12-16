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
import restaurant_management_system.eNum.OrderStatus;


import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long code;

    double totalPrice;

    Long totalNumber;

    @Enumerated(EnumType.STRING)
    OrderStatus status;

    @Column(name = "message", nullable = true, columnDefinition = "VARCHAR(255)")
    private String message;

    @CreatedDate
    private LocalDateTime createdDate ;

    @LastModifiedDate
    private LocalDateTime LastModified ;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;


//_______________________relation__________________
    @ManyToMany(fetch = FetchType.EAGER)
    List<Product> products;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users users;

  @OneToMany(mappedBy = "order" ,fetch = FetchType.EAGER)
    List<OrderItem> orderItems;

}
