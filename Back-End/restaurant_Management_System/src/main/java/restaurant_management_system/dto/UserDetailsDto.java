package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.model.Users;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "USER DETAILS DTO" , description = "This is the User Details DTO class contain [username ,age ,phoneNumber ,address")
public class UserDetailsDto {

    @Schema(description = "User Id")
    private Long id;

    @Schema(description = "User Name")
    private String username;

    @Schema(description = "User Age")
    private int age;

    @Schema(description = "User phone Number")
    private String phoneNumber;

    @Schema(description = "User Address")
    private String address;

//____________________relation___________________________

    @Schema(hidden = true)
    private Users users;
}
