package restaurant_management_system.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import restaurant_management_system.dto.ContactInfoDto;
import restaurant_management_system.service.contactInfoService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/contactInfo")
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
    @PreAuthorize("hasAnyRole('USER' ,'ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<Void> saveContactInfo(@RequestBody @Valid ContactInfoDto contactInfoDto) throws URISyntaxException
    {
        contactInfoService.saveContactInfo(contactInfoDto);
        return  ResponseEntity.created(new URI("/saveContactInfo")).build();
    }

//TODO ____________________ get All Contact Info ___________________________
//TODO _____________________________________________________________________
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<ContactInfoDto>> getAll()
    {
        return ResponseEntity.ok().body(contactInfoService.getContactInfo());
    }



//TODO ____________________ get Contact Info By Id_____________________________
//TODO ________________________________________________________________________
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getById")
    public ResponseEntity<List<ContactInfoDto>> getById()
    {
        return ResponseEntity.ok().body(contactInfoService.getContactInfoById());
    }



//TODO ____________________ get ContactInfo By Id_________________________________
//TODO ___________________________________________________________________________
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
