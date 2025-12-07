package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import restaurant_management_system.model.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactInfoDto {

    private Long id;
    @NotBlank(message = "ContactInfo.Name.NotBlank")
    private String name ;

    private String email ;

    private String subject ;

    private String message ;

    private Long userId ;

    private String replyMessage;

//____________________relation___________________________
    @Lazy
    Users users;

   public void setUser(Users users)
    {
        this.userId = users.getId();
        this.users = null;
    }




}