package org.example.kaszmaginnovate.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("KaszMag-Innovate Application")
                .version("1.0")
                .description("This is a RESTful API for the KaszMag-Innovate Application.")
                .contact(new Contact()
                        .name("Kaszás Viktor (W8A1QH), Magyarosi Andor Máté (ZYCMC6)")
                        .url("https://www.used-car-app.com")
                        .email("w8a1qhl@haéégato.uni-neumann.hu")
                )
        );
    }
}