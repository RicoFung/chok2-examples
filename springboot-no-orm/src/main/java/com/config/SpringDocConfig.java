package com.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "basicAuth", scheme = "basic", in = SecuritySchemeIn.HEADER)
public class SpringDocConfig
{
}
