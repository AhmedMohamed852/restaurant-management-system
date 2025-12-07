package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefDto {


    private Long id;
    @NotNull(message = "Chef.Name.NotNull")
    private String name ;
    private String spec ;
    private String logoPath ;
    private String faceLink ;
    private String tweLink ;
    private String instaLink ;

//____________________relation___________________________

}
