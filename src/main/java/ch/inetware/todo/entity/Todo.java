package ch.inetware.todo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@ApiModel(description = "Ein Eintrag in einer Todo-Liste")
public class Todo {

  @Id
  @GeneratedValue (strategy= GenerationType.IDENTITY)
  @JsonProperty
  @ApiModelProperty(notes = "Eindeutige ID des Todo-Eintrags", example = "1", allowEmptyValue = true, readOnly = true, position = 1)
  private Long id;

  @JsonProperty
  @NotNull
  @ApiModelProperty(notes = "Titel des Todo-Eintrags",example = "Dies ist ein Todo-Titel", position = 2)
  @Min(3) @Max(256)
  private String title;

  @JsonProperty
  @ApiModelProperty(notes = "Beschreibung des Todo-Eintrags",example = "Ein wunderschönes Beispiel.", position = 3)
  @Min(0) @Max(8192)
  private String description;

  public Todo() {}

  public Todo(String title, String description) {
    this.title = title;
    this.description = description;
  }

}
