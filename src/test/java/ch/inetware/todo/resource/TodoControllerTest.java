package ch.inetware.todo.resource;

import io.restassured.specification.RequestSpecification;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class TodoControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private RequestSpecification documentationSpec;

  @Test
  public void listTodos() throws Exception {
    given(this.documentationSpec).filter(document("list-todos"))
            .when().port(this.port).get("/v1/todos").then()
            .assertThat().statusCode(is(200));
  }

  @Test
  @Ignore
  public void addOneTodo() throws Exception {
    given(this.documentationSpec).filter(document("add-one-todo"))
            .contentType("application/json")
            .body("{\"title\": \"Das ist ein Titel\", \"description\": \"Das ist ein schönes Todo\"}")
            .when().port(this.port).post("/v1/todos").then()
            .assertThat().statusCode(is(200));
  }

  @Test
  public void getOneTodo() throws Exception {
    given(this.documentationSpec).filter(document("get-one-todo"))
            .when().port(this.port).get("/v1/todos/1").then()
            .assertThat().statusCode(is(200));
  }
}
