package ch.inetware.todo.config.restdocs;

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsRestAssuredConfigurationCustomizer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentationConfigurer;
import org.springframework.restdocs.templates.TemplateFormats;

@TestConfiguration
public class CustomizationConfiguration
        implements RestDocsRestAssuredConfigurationCustomizer {

  @Override
  public void customize(RestAssuredRestDocumentationConfigurer configurer) {
    configurer.snippets().withTemplateFormat(TemplateFormats.asciidoctor());
  }

}