package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.eNum.RolesEnum;
import restaurant_management_system.model.ContactInfo;
import restaurant_management_system.model.Role;
import restaurant_management_system.model.UserDetails;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDto {

    private Long id;

    @NotBlank(message = "username.Must.Not.Be.Blank")
    @NotNull(message = "username.Must.Not.Be.Null")
    private String username ;
    @NotBlank(message = "password.Must.Not.Be.Blank")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password.must.be.at.least.8characters"
    )
    private String password;

    private RolesEnum role;


//____________________relation___________________________

  private   UserDetails userDetails;

  private List<ContactInfo> contactInfo;

  private  List<Role> Roles;

}
