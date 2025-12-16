package restaurant_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.UsersDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.AuthService;
import restaurant_management_system.vm.LoginRequestVm;
import restaurant_management_system.vm.LoginResponseVm;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("api/auth")
@Tag(name = "Auth Controller", description = "API for Authentication")
public class AuthController {

    private AuthService authService;
    @Autowired
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }
//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO ____________________ user SignUp ______________________________
//TODO _______________________________________________________________

// TODO -----------> SWAGGER {
@Operation(
            summary = "SignUp",
            description = "API To Create Account",

          responses = {
                    @ApiResponse( responseCode = "200", description = "Http Status SignUp Success",
                    content = @Content(schema = @Schema(implementation = MessageResponse.class))
                                ),

                    @ApiResponse( responseCode = "500", description = "Http Status User Already Exists",
                     content = @Content(schema = @Schema(implementation = MessageResponse.class))
                                ),

                         }
             )
// TODO               SWAGGER }     <---------------------

    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody @Valid UsersDto usersDto) throws URISyntaxException
    {
        authService.signUp(usersDto);
        return ResponseEntity.created(new URI("/signUp")).build();
    }



//TODO ____________________ user Login _________________________________
//TODO _______________________________________________________________

    // TODO ----------->  SWAGGER {
    @Operation(
            summary = "Login",
            description = "API Login To Account",

            responses = {
                    @ApiResponse( responseCode = "200", description = "Http Status Login Success",
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    ),

                    @ApiResponse( responseCode = "500", description = "Http Status wrong password",
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    ),

                     }
    )
// TODO               SWAGGER  } <---------------------

    @PostMapping("/login")
    public ResponseEntity<LoginResponseVm> login(@RequestBody @Valid LoginRequestVm loginRequestVm)
    {
        return ResponseEntity.ok().body(authService.login(loginRequestVm));
    }

//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________

}
