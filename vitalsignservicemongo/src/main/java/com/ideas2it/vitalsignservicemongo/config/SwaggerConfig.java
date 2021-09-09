package com.ideas2it.vitalsignservicemongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;

/**
 * SwaggerConfig service is used to generate EndPoint Detail Documentation.
 * 
 * @author Vigneshwaran N
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/vitalSign/*"))
				.apis(RequestHandlerSelectors.basePackage("com.ideas2it")).build().apiInfo(apiDetails());
	}

	/**
	 * apiDetails method is used to send the details about the API.
	 * 
	 * @author Vigneshwaran N
	 */
	private ApiInfo apiDetails() {
		return new ApiInfoBuilder().title("Health Care VitalSign Detail Management")
				.description("Detail documentation for Vitsl Rest API").license("HEALTHCARE").version("1.0").build();
	}
}
