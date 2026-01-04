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
import restaurant_management_system.dto.ContactInfoDto;
import restaurant_management_system.helper.MessageResponse;
import restaurant_management_system.service.contactInfoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/contactInfo")
@Tag(name = "Contact Info Controller", description = "API For Manage Contact Info")
public class contactInfoController {

    private contactInfoService contactInfoService;

    @Autowired
    public contactInfoController(contactInfoService contactInfoService)
    {
        this.contactInfoService = contactInfoService;
    }

//TODO ____________________IMPLEMENTATION_______________________________
//TODO _________________________________________________________________



//TODO ____________________ save Contact Info ___________________________
//TODO __________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Save Contact Info",
            description = "API To Save Contact Info",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status id Must Be Null" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Void> saveContactInfo(@RequestBody @Valid ContactInfoDto contactInfoDto) throws URISyntaxException
    {
        contactInfoService.saveContactInfo(contactInfoDto);
        return  ResponseEntity.created(new URI("/saveContactInfo")).build();
    }

//TODO ____________________ get All Contact Info ___________________________
//TODO _____________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get All Contact Info",
            description = "API To Get All Contact Info",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ContactInfoDto.class))
                                ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Contact Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ContactInfoDto>> getAll()
    {
        return ResponseEntity.ok().body(contactInfoService.getContactInfo());
    }


//TODO ____________________ get Contact Info By Id_____________________________
//TODO ________________________________________________________________________

    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Get Contact Info By Id",
            description = "API Get Contact Info By Id" ,

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success" ,
                            content = @Content(schema = @Schema(implementation = ContactInfoDto.class))
                    ) ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status Contact Not Found" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getById")
    public ResponseEntity<List<ContactInfoDto>> getById()
    {
        return ResponseEntity.ok().body(contactInfoService.getContactInfoById());
    }



//TODO ____________________ get ContactInfo By Id_________________________________
//TODO ___________________________________________________________________________


    // TODO -----------> SWAGGER {
    @Operation(
            summary = "Reply Contact Info",
            description = "API Reply Contact Info",

            responses = {
                    @ApiResponse( responseCode = "200" ,description = "Http Status Success") ,

                    @ApiResponse( responseCode = "500" ,description = "Http Status id Must Not Be Empty" ,
                            content = @Content(schema = @Schema(implementation = MessageResponse.class))
                    )
            }

    )
// TODO               SWAGGER }     <---------------------

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/reply")
    public ResponseEntity<Void> reply(@RequestBody ContactInfoDto contactInfoDto)
    {
        contactInfoService.updateReplyMessage(contactInfoDto);
        return ResponseEntity.noContent().build();
    }




//TODO ____________________________ FINISHED _________________________________________
//TODo _______________________________________________________________________________



}
