package ch.inetware.todo.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

  @RequestMapping("/hello")
  public String index() {
    return "Greetings from Spring Boot!";
  }

}
