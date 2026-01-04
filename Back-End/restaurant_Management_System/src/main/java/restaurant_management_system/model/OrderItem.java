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

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class OrderItem extends  BaseEntity {


    private Long quantity;

    double PriceThisItem;

//____________________relation___________________________

    @ManyToOne(fetch = FetchType.EAGER)
    private Orders order;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

}
