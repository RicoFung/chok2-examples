package com.config;

import org.apache.http.HttpHeaders;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfig
{
	@Bean
	public OpenAPI openAPI()
	{
		return new OpenAPI()
				.info(new Info()
						.title("Chok Resource API")
						.description("SpringDoc API 演示")
						.version("v1.0.0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
						)
//				.externalDocs(new ExternalDocumentation().description("Edevp-Stpre").url("http://www.edevp.cn"))
				.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
				.components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION,
						new SecurityScheme().name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP)
								.scheme("bearer").bearerFormat("JWT")));
		// .schemaRequirement(HttpHeaders.AUTHORIZATION, this.securityScheme());
	}

	@Bean
	public GroupedOpenApi v1Api()
	{
		return GroupedOpenApi.builder().group("v1").pathsToMatch("/api/v1/**").build();
	}
	
	@Bean
	public GroupedOpenApi v2Api()
	{
		return GroupedOpenApi.builder().group("v2").pathsToMatch("/api/v2/**").build();
	}
	
	@Bean
	public GroupedOpenApi v3Api()
	{
		return GroupedOpenApi.builder().group("v3").pathsToMatch("/api/v3/**").build();
	}
}
