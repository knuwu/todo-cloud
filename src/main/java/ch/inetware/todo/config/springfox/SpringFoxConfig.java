  package ch.inetware.todo.config.springfox;

  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import springfox.documentation.builders.ApiInfoBuilder;
  import springfox.documentation.builders.PathSelectors;
  import springfox.documentation.builders.RequestHandlerSelectors;
  import springfox.documentation.service.ApiInfo;
  import springfox.documentation.service.Contact;
  import springfox.documentation.spi.DocumentationType;
  import springfox.documentation.spring.web.plugins.Docket;
  import springfox.documentation.swagger2.annotations.EnableSwagger2;

  @Configuration
  @EnableSwagger2
  public class SpringFoxConfig {
      @Bean
      public Docket apiDocket() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .select()
                  .apis(RequestHandlerSelectors.basePackage("ch.inetware"))
                  .paths(PathSelectors.any())
                  .build().apiInfo(getApiInfo());
      }

      private ApiInfo getApiInfo() {
          ApiInfoBuilder builder = new ApiInfoBuilder();
          return builder.title("Todo - A Simple ToDo List")
          .description("API to access and manipulate a simple todo list")
          .version("1.0")
          .termsOfServiceUrl("terms of service url")
          .contact(new Contact("Ralph Goebel", "https://www.inetware.ch", "ralph.goebel@inetware.ch"))
          .license("GNU 1.0").build();

      }
  }