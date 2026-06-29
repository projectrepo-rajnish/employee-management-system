package com.rajnish.ems.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeeManagementOpenAPI() {

    // Add info to swagger page
        Info info = new Info();
        info.setTitle("Employee Management System API");
        info.setDescription("Production-style practice project APIs for Employee Management System");
        info.setVersion("1.0.0");
    // Add authorize button to swagger page which helps to pass username and pass in call
        SecurityScheme basicAuthScheme = new SecurityScheme();
        basicAuthScheme.setType(SecurityScheme.Type.HTTP);
        basicAuthScheme.setScheme("basic");

        Components components = new Components();
        components.addSecuritySchemes("basicAuth", basicAuthScheme);

        OpenAPI openAPI = new OpenAPI();
        openAPI.setInfo(info);
        openAPI.setComponents(components);

        return openAPI;
    }
}