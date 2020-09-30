package com.warehouse.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public Docket get(){
		return new Docket(DocumentationType.SWAGGER_2)/*
				.select()
					.paths(PathSelectors.ant("/api/**"))
				.build()*/
				.apiInfo(returnApiInfo());
	}

	private ApiInfo returnApiInfo(){
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		return apiInfoBuilder
				.title("piano warehouse API")
				.description("")
				.contact(new Contact("Rafal", "","rafal.antas96@gmail.com"))
				.version("1.00")
				.build();
	}

}
