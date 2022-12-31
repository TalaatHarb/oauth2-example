package net.talaatharb.examplebackend.api;

import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.facades.TodoFacade;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class TodoRestController implements  TodoAPI{
    private final TodoFacade todoFacade;

    @Override
    public TodoDTO createTodo(TodoDTO todo) {
        if(!todo.isValidToCreate()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, APIConstants.TODO_INVALID);
        }
        return todoFacade.createTodo(todo);
    }
}
