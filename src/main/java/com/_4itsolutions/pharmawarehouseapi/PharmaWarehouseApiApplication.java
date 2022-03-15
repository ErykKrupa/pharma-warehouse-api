package com._4itsolutions.pharmawarehouseapi;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PharmaWarehouseApiApplication {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/" + url + "/**"))
                .build();
    }

    private String url;

    @Value("${url}")
    public void setUrl(String url) {
        this.url = url;
    }

    public static void main(String[] args) {
        SpringApplication.run(PharmaWarehouseApiApplication.class, args);
    }

}
