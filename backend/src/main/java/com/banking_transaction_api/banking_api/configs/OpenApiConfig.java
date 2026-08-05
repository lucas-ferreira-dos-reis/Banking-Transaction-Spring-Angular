package com.banking_transaction_api.banking_api.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Financial Transfer Scheduling API")
                        .version("1.0.0")
                        .description(
                                "REST API for scheduling financial transfers and calculating dynamic fees based on transfer dates."));
    }

}
