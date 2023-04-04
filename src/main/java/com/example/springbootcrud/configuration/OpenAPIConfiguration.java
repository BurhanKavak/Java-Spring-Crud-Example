//package com.example.springbootcrud.configuration;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenAPIConfiguration {
//
//
//    @Bean
//    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
//                                @Value("${application-version}") String version) {
//
//        return new OpenAPI()
//                .info(new Info()
//                        .title("CRUD projesi")
//                        .version(version)
//                        .description(description)
//                        .license(new License().name("Burhan Kavak Open API Licence"))
//                );
//
//    }
//}
package com.example.springbootcrud.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;


import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Configuration
public class OpenAPIConfiguration {

    final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")));
    }
}