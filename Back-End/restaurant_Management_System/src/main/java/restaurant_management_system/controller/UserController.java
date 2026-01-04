package restaurant_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.UserDetailsDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.UserDetailService;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "API For Manage Users Details")
public class UserController {

    private UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService)
    {
        this.userDetailService = userDetailService;
    }



//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________ add User Detail ________________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Check User Has Details",
            description = "API To Check User Has Details",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = Boolean.class))) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status User Not Found",
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @GetMapping("/isUserHasDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> isUserHasDetails()
    {
        return ResponseEntity.ok(userDetailService.isUserHasDetails());
    }


//TODO _________________ update User Detail _____________________________
//TODO ______________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Add User Detail",
            description = "API To Add User Detail",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status User Not Found",
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PostMapping("/addUserDetail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> addUserDetails(@RequestBody UserDetailsDto userDetailsDto)
    {
        userDetailService.addUserDetail(userDetailsDto);
        return ResponseEntity.noContent().build();
    }


//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________

}
