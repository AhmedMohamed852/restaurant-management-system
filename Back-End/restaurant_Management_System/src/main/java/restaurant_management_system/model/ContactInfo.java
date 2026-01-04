package restaurant_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ContactInfo extends BaseEntity {

    private String name ;

    private String email ;

    private String subject ;

    private String message ;


    private String replyMessage;

//____________________relation___________________________

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id" )
    Users users;

}
