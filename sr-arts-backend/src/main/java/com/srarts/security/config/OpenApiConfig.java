//package com.srarts.config;
package com.srarts.security.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI srArtsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SR Arts API")
                        .version("v1")
                        .description(
                                "Backend API for SR Arts online customization and ordering platform"
                        ));
    }
}