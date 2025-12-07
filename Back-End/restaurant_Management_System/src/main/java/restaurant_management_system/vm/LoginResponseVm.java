package restaurant_management_system.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import restaurant_management_system.eNum.RolesEnum;
import restaurant_management_system.model.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseVm {
    private String token;
    Long id;


}
