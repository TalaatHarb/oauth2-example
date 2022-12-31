package net.talaatharb.examplebackend.api;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(APIConstants.TODOS_URL)
public interface TodoAPI {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    TodoDTO createTodo(@RequestBody TodoDTO todo);

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    Page<TodoDTO> getTodos(Pageable pageable);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TodoDTO getTodo(@PathVariable("id") Long id);
}
