package restaurant_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.UserDetailsDto;
import restaurant_management_system.service.UserDetailService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService)
    {
        this.userDetailService = userDetailService;
    }



//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________





//TODO _________________addUserDetail________________________________
//TODO ______________________________________________________________

    @GetMapping("/isUserHasDetails")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> isUserHasDetails()
    {
        return ResponseEntity.ok(userDetailService.isUserHasDetails());
    }

//TODO _________________updateUserDetail_____________________________
//TODO ______________________________________________________________

    @PostMapping("/addUserDetail")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updateUserDetail(@RequestBody UserDetailsDto userDetailsDto)
    {
        userDetailService.addUserDetail(userDetailsDto);
        return ResponseEntity.noContent().build();
    }

}
