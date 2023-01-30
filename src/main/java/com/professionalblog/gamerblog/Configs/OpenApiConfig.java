package com.professionalblog.gamerblog.Configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI () {
        return new OpenAPI()
                .info(new Info()
                        .title("Professional Blog RESTful API")
                        .version("1.0")
                        .description("API Restful of a professional blog")
                        .termsOfService("https://www.linkedin.com/in/luan-colombo89/")
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/luancolombo/professional_blog/blob/main/license")
                        )
                );
    }
}
