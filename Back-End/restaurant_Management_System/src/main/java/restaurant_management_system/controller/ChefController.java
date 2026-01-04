package restaurant_management_system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.ChefDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.ChefService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/chef")
@Tag(name = "Chef Controller", description = "API For Manage Chefs")
public class ChefController {

    private ChefService chefService;
    @Autowired
    public ChefController(ChefService chefService)
    {
        this.chefService = chefService;
    }

//TODO ____________________IMPLEMENTATION___________________________
//TODO _____________________________________________________________



//TODO ____________________ save Chef ______________________________
//TODO _____________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save Chef",
            description = "API To Save Chef" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ChefDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Chef Already Exists" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/saveChef")
    public ResponseEntity<ChefDto> saveChef(@RequestBody @Valid ChefDto chefDto) throws URISyntaxException
    {
        return ResponseEntity.created(new URI("/saveChef")).body(chefService.saveChef(chefDto));
    }



//TODO ____________________ get All Chefs __________________________
//TODO _____________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Chefs",
            description = "API To Get All Chefs",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ChefDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Chef Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ChefDto>> getAll()
    {
        return ResponseEntity.ok().body(chefService.getAllChefs());
    }


//TODO ____________________________FINISHED________________________________________________
//TODo ____________________________________________________________________________________
}
