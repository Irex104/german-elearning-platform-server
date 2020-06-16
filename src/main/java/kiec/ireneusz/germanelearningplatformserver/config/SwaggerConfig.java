package kiec.ireneusz.germanelearningplatformserver.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.any;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private static final String BasePackage = "kiec.ireneusz.germanelearningplatform";
    private static final String HEADER = "header";

    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(Principal.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .useDefaultResponseMessages(false);

        docket.select()
                .apis(RequestHandlerSelectors.basePackage(BasePackage))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION, HEADER);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
        return Lists.newArrayList(new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
    }

}
