package restaurant_management_system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "CHEF DTO" , description = "This is the Chef DTO class contain [name ,spec ,logoPath ,faceLink ,tweLink ,instaLink")
public class ChefDto {


    private Long id;

    @NotNull(message = "Chef.Name.NotNull")
    @Schema(description = "Chef Name")
    private String name ;

    @Schema(description = "Chef Specialization")
    private String spec ;

    @Schema(description = "Chef Logo Path")
    private String logoPath ;

    @Schema(description = "Chef Social Media Links")
    private String faceLink ;

    @Schema(description = "Chef Social Media Links")
    private String tweLink ;

    @Schema(description = "Chef Social Media Links")
    private String instaLink ;

//____________________relation___________________________

}
