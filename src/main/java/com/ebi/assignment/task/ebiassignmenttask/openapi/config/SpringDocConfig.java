package com.ebi.assignment.task.ebiassignmenttask.openapi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig {

	@Bean
	  public OpenAPI customOpenAPI() {
				List<String> readWrite = new ArrayList<String>();
		readWrite.add("read");
		readWrite.add("write");
		
	    return new OpenAPI()
	            .components(new Components().addSecuritySchemes("bearer-jwt",
	                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
	                    .in(SecurityScheme.In.HEADER).name("Authorization")))
	            .info(new Info().title("Person Management API").version("v1.0"))
	            .addSecurityItem(
	                    new SecurityRequirement().addList("bearer-jwt", readWrite));
	  }

}