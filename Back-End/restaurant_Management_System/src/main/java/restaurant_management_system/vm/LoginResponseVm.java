package restaurant_management_system.vm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseVm {
    private String token;
    Long id;


}
