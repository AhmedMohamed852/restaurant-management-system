package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "CONTACT INFO DTO" , description = "This is the ContactInfo DTO class contain [name ,email ,subject ,message ,userId")
public class ContactInfoDto {

    private Long id;

    @Schema(description = "ContactInfo Name")
    @NotBlank(message = "ContactInfo.Name.NotBlank")
    private String name ;

    @Schema(description = "ContactInfo Email")
    private String email ;

    @Schema(description = "ContactInfo Subject")
    private String subject ;

    @Schema(description = "ContactInfo Message")
    private String message ;

    @Schema(description = "ContactInfo UserId")
    private Long userId ;

    @Schema(description = "ContactInfo Reply Message")
    private String replyMessage;

//____________________relation___________________________
    @Lazy
    @Schema(hidden = true)
    Users users;

    @Schema(hidden = true)
   public void setUser(Users users)
    {
        this.userId = users.getId();
        this.users = null;
    }



}