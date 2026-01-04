package restaurant_management_system.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_s")
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
@Schema(name = "USERS DTO" , description = "This is the Users DTO class contain [username ,password ,createdDate ,LastModified ,createdBy ,updatedBy")
public class Users extends  BaseEntity {

    @Column(unique = true)
    private String username ;

    @Schema(description = "The password of the Users")
    private String password ;


//____________________relation___________________________
    @OneToOne(mappedBy = "users" , cascade = CascadeType.ALL)
    @Schema(hidden = true)
    private UserDetails userDetails;

    @OneToMany(mappedBy = "users",fetch = FetchType.EAGER)
    @Schema(hidden = true)
    private List<ContactInfo> contactInfo;


    @ManyToMany(fetch = FetchType.EAGER)
    @Schema(hidden = true)
    private List<Role> Roles;

    @OneToMany(mappedBy = "users")
    @Schema(hidden = true)
   private List<Orders> orders;
}
