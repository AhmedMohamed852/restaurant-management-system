package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.ChefDto;
import restaurant_management_system.service.ChefService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/chef")
public class ChefController {

    private ChefService chefService;
    @Autowired
    public ChefController(ChefService chefService)
    {
        this.chefService = chefService;
    }

//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO ____________________ save Chef _________________________________
//TODO _____________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveChef")
    public ResponseEntity<ChefDto> saveChef(@RequestBody @Valid ChefDto chefDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveChef")).body(chefService.saveChef(chefDto));
    }



//TODO ____________________ get All Chefs _________________________________
//TODO _____________________________________________________________
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ChefDto>> getAll()
    {
        return ResponseEntity.ok().body(chefService.getAllChefs());
    }


//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________
}
