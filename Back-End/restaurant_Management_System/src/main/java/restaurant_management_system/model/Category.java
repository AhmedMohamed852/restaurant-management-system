package restaurant_management_system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name ;

    String logo;

    String flag;

    @CreatedDate
    private LocalDateTime createdDate ;

    @LastModifiedDate
    private LocalDateTime LastModified ;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;


//____________________relation___________________________

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY )
    List<Product> products;


}
