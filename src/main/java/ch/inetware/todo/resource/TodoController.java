package ch.inetware.todo.resource;

import ch.inetware.todo.data.TodoRepository;
import ch.inetware.todo.entity.Todo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@Api(description = "Ressource zum Anlegen, Bearbeiten, Lesen und Löschen von Todo-Einträgen")
@RequestMapping("/v1/todos")
public class TodoController {

  @Resource
  private TodoRepository todoRepository;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  @ApiOperation("Liefert eine Liste alle auf dem System gespeicherten Todo-Einträge.")
  public List<Todo> getTodos(){
    return (List<Todo>)todoRepository.findAll();
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  @ApiOperation("Speichert einen neuen Todo-Eintrag.")
  public void newTodo(@RequestBody Todo newTodo) {
    todoRepository.save(newTodo);
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}", produces = "application/json")
  @ApiOperation("Liest einen Todo-Eintrag über die eindeutige ID des Eintrags.")
  @ApiResponses(
          @ApiResponse(code = 404,message = "Der Todo-Eintrag kann nicht gefunden werden.")
  )
  public Todo getTodo(@PathVariable Long id) {
    return todoRepository.findById(id)
            .orElseThrow(()-> new TodoNotFoundException(id));
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = "application/json")
  @ApiOperation("Bearbeitet einen bestehenden Todo-Eintrag.")
  public Todo replaceTodo(@RequestBody Todo changedTodo, @PathVariable Long id) {
    return todoRepository.findById(id)
            .map(todo -> {
              todo.setTitle(changedTodo.getTitle());
              todo.setDescription(changedTodo.getDescription());
              return todoRepository.save(todo);
            })
            .orElseThrow(()-> new TodoNotFoundException(id));
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}", produces = "application/json")
  @ApiOperation("Löscht einen Todo-Eintrag über die eindeutige ID des Eintrags.")
  public void deleteTodo(@PathVariable Long id) {
    todoRepository.deleteById(id);
  }

}
