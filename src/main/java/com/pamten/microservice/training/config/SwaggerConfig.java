/**
 * 
 */
package com.pamten.microservice.training.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author aakash_gupta
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
						.basePackage("com.pamten.microservice.training.controller"))
				.paths(regex("/api.*")).build().apiInfo(metaData());
	}

	private static ApiInfo metaData() {

		return new ApiInfo("Student Controller",
				"This controller is responsible for basic CRUD operations",
				"1.0", "", new Contact("Aakash Gupta", "https://abc.com", ""),
				"", "", Collections.emptyList());

	}

}
