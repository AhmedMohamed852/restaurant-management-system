package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.enums.RolesEnum;
import restaurant_management_system.model.ContactInfo;
import restaurant_management_system.model.Role;
import restaurant_management_system.model.UserDetails;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

// TODO -----------> SWAGGER {
@Schema(
        name = "USER DTO" ,
        description = "This Class Contain --> [ id ,username ,password ]"
)
// TODO               SWAGGER  } <---------------------

public class UsersDto {

    private Long id;


    // TODO -----------> SWAGGER {
        @Schema(
                name = "userName" ,
                description = "email Or username Of User" ,
                example = "Ahmed Mohamed"
                )
// TODO               SWAGGER  } <---------------------

    @NotBlank(message = "username.Must.Not.Be.Blank")
    @NotNull(message = "username.Must.Not.Be.Null")
    private String username ;

    // TODO -----------> SWAGGER {
    @Schema(
            name = "password" ,
            description = "password Of Account" ,
            example = "Ahmed@123"
    )
// TODO               SWAGGER  } <---------------------
    @NotBlank(message = "password.Must.Not.Be.Blank")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password.must.be.at.least.8characters"
    )
    private String password;


    @Schema(hidden = true)
    private RolesEnum role;


//____________________relation___________________________

  private   UserDetails userDetails;

  private List<ContactInfo> contactInfo;

  private  List<Role> Roles;

}
