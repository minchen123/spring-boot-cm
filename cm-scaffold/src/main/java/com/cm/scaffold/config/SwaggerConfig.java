package com.cm.scaffold.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description 文档接口工具
 * @Author cm
 * @Date 2020/4/22 9:41
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false)
                .name("appid").description("appid").modelRef(new ModelRef("string")).parameterType("path").required(false)
                .build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后台小分队")
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.cm.scaffold.controller"))
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build().globalOperationParameters(pars)
                .pathMapping("/")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("XXX-用户中心 APIs")
                .description("后台文档接口")
                .contact("XXX")
                .version("0.1")
                .build();

    }
}
