package springseller.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("springseller.webservice.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Seller API")
                .description("Api para simulação de sistema de vendas")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact() {
        return new Contact("Filipe Moura",
                "http://github.com/Filipey",
                "filipeasm18@gmail.com");
    }

    public ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global",
                "accesEverything");

        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference securityReference = new SecurityReference("JWT", scopes);

        List<SecurityReference> securityReferenceList = new ArrayList<>();
        securityReferenceList.add(securityReference);

        return securityReferenceList;
    }
}
