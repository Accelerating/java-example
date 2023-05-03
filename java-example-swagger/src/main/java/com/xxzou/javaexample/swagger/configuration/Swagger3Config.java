package com.xxzou.javaexample.swagger.configuration;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOpenApi
public class Swagger3Config implements WebMvcConfigurer {

    @Bean
    public Docket createApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 指定扫描的包，不指定会扫描出 spring 框架的接口，指定错误会导致接口扫描不出来
                .apis(RequestHandlerSelectors.basePackage("com.xxzou.javaexample.swagger.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .globalRequestParameters(getGlobalRequestParameters());//加入通用入参
    }

    /**
     * API 文档基础信息，包括标题、联系人等
     * @return ApiInfo
     */
    private ApiInfo createApiInfo() {
        return new ApiInfoBuilder()
                .title("example-swagger-api")
                .description("example-swagger-api-description")
                .contact(new Contact("xxzou", "http://www.xxzou.com", "xuan@xxzou.com"))
                .build();
    }



    //生产通过接口入参
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("用户凭证")
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .build());
        return parameters;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor();
    }
}
