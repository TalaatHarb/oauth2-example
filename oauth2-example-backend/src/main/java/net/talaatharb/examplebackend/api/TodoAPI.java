package net.talaatharb.examplebackend.api;

import com.fasterxml.jackson.annotation.JsonView;
import net.talaatharb.examplebackend.dtos.JsonViewLevel;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping(APIConstants.TODOS_URL)
public interface TodoAPI {

    @PostMapping("")
    @JsonView(JsonViewLevel.Details.class)
    @ResponseStatus(HttpStatus.CREATED)
    TodoDTO createTodo(@RequestBody TodoDTO todo, Authentication authentication);

    @GetMapping("")
    @JsonView(JsonViewLevel.Summary.class)
    @ResponseStatus(HttpStatus.OK)
    Page<TodoDTO> getTodos(Pageable pageable, Authentication authentication);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(JsonViewLevel.Details.class)
    TodoDTO getTodo(@PathVariable("id") Long id, Authentication authentication);
}
