package com.yang.basicmodel;

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
* @author  yf 
* swagger-ui配置文件设置
* 本机测试访问方式http://IP:port/swagger-ui.html
* 正式部署tomcat访问方式http://IP:port/{项目名}/swagger-ui.html
* @date 创建时间：2017年12月14日 上午10:28:39 
* @version 1.0   
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        //前后端分离式项目需要token验证，swagger-ui添加header添加令牌
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("x-access-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(tokenPar.build());
        // 选择那些路径和api会生成document
        return new Docket(DocumentationType.SWAGGER_2).select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                // 对所有路径进行监控
                .paths(PathSelectors.any()).build().globalOperationParameters(pars).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("后台接口文档与测试").description("这是一个给app端人员调用server端接口的测试文档与平台").version("1.0.0")
                .termsOfServiceUrl("http://terms-of-services.url")
                // .license("LICENSE")
                // .licenseUrl("http://url-to-license.com")
                .build();
    }

}
