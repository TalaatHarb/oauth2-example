package net.talaatharb.examplebackend.api;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(APIConstants.TODOS_URL)
public interface TodoAPI {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public abstract TodoDTO createTodo(@RequestBody TodoDTO todo);
}
