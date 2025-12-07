package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.service.AuthService;
import restaurant_management_system.vm.LoginRequestVm;
import restaurant_management_system.vm.LoginResponseVm;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }
//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO ____________________userSignUp_________________________________
//TODO _______________________________________________________________

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UsersDto usersDto) throws URISyntaxException
    {
        authService.signUp(usersDto);
        return ResponseEntity.created(new URI("/signUp")).build();
    }



//TODO ____________________userLogin_________________________________
//TODO _______________________________________________________________
    @PostMapping("/login")
    public ResponseEntity<LoginResponseVm> login(@RequestBody @Valid LoginRequestVm loginRequestVm)
    {
        return ResponseEntity.ok().body(authService.login(loginRequestVm));
    }

}
