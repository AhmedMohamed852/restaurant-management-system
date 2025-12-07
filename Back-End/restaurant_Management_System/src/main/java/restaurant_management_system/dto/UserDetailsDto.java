package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class UserDetailsDto {

    private Long id;

    private String username;

    private int age;

    private String phoneNumber;

    private String address;

//____________________relation___________________________

    private Users users;
}
