package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import restaurant_management_system.eNum.RolesEnum;
import restaurant_management_system.model.Users;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    RolesEnum code;

//____________________relation___________________________

    List<Users> users;
}
