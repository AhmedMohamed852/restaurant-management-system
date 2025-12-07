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
@Table(name = "user_s")
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username ;

    private String password ;


    @CreatedDate
    private LocalDateTime createdDate ;

    @LastModifiedDate
    private LocalDateTime LastModified ;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

//____________________relation___________________________
    @OneToOne(mappedBy = "users" , cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<ContactInfo> contactInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> Roles;

    @OneToMany(mappedBy = "users")
   private List<Orders> orders;
}
