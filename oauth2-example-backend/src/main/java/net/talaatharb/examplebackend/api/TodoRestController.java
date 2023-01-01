package net.talaatharb.examplebackend.api;

import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.facades.TodoFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TodoRestController implements  TodoAPI{
    private final TodoFacade todoFacade;

    @Override
    public TodoDTO createTodo(final TodoDTO todo, final Authentication authentication) {
        if(!todo.isValidToCreate()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, APIConstants.TODO_INVALID);
        }
        todo.setUserId(UUID.fromString(authentication.getName()));
        return todoFacade.createTodo(todo);
    }

    @Override
    public Page<TodoDTO> getTodos(final Pageable pageable, final Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        return todoFacade.getTodos(userId, pageable);
    }

    @Override
    public TodoDTO getTodo(final Long id, final Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        return todoFacade.getTodo(id, userId);
    }
}
