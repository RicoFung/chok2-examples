package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

// 默认（只能扫描project内的bean）
//@SpringBootApplication
// 自定义（可扫描project外的bean）
@ComponentScan(basePackages={"com","chok.common","chok.component","chok.lock"})
@EnableAutoConfiguration
@EnableCaching
public class Application
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
}
