package org.bsukhodoev.fuzbuzdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class FizzBuzzDemoApplication {
    @Autowired
    private Environment env;

	public static void main(String[] args) {
		SpringApplication.run(FizzBuzzDemoApplication.class, args);
	}

	@Bean
	public Docket api() {
        final ApiInfo apiInfo = new ApiInfo(
                env.getProperty("api.documentation.header"),
                env.getProperty("api.documentation.description"),
                env.getProperty("api.documentation.version"),
                null,
                new Contact(
                        env.getProperty("api.documentation.contact.name"),
                        env.getProperty("api.documentation.contact.url"),
                        env.getProperty("api.documentation.contact.email")),
                env.getProperty("api.documentation.license.name"),
                env.getProperty("api.documentation.license.url"),
                new ArrayList<>()
        );

		return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
				.paths(PathSelectors.any())
				.build();
	}
}
