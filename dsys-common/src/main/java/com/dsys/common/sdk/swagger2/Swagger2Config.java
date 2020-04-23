package com.dsys.common.sdk.swagger2;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Title: Swagger2Config
 * @author shilp
 * Company:
 * Copyright: Copyright (c)
 * @version 1.0
 * @Description: TODO
 * @created 2020/4/22 16:27
 */
@Configuration
@EnableSwagger2
public class Swagger2Config{
    
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dsys"))
                .paths(PathSelectors.regex("/cim/.*"))
                .build();
    }
    
    private ApiInfo apiInfo (){
        return new ApiInfoBuilder()
                .title("dsys接口文档")
                .description("restFUL风格API")
                .termsOfServiceUrl("http://localhost:8080/dsys")
                .version("0.0.1")
                .build();
    }
}
