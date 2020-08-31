package com.psybergate.login_page.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerUiConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true)
                .globalOperationParameters(globalParameterList()).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build();
    }
    private List<Parameter> globalParameterList() {
        Parameter authTokenHeader = new ParameterBuilder().name("Authorization") // name of the header
                .modelRef(new ModelRef("string")) // data-type of the header
                .required(false) // required/optional
                .parameterType("header") // for query-param, this value can be 'query'
                .description("Basic Auth Token").build();
        return Collections.singletonList(authTokenHeader);
    }

}
