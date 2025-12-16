package restaurant_management_system.config.swaggerConfig;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info =
                    @Info(
                              title = "Restaurant Management System", version = "1.0",
                              description = "This is a Restaurant Management System API" ,
                        contact =
                        @Contact(
                                name = "Ahmed Mohamed",
                                 email = "e01018478430@gmail.com" ,
                                 url = "https://www.linkedin.com/in/ahmedmohammed-jv/"
                                ),
                        license =
                        @License(
                                name = "Restaurant License",
                                url = "http://localhost:4200"
                                )
                         )
                    )
public class SwaggerConfig {
}
