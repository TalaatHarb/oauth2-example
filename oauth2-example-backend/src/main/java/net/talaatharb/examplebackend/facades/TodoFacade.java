package net.talaatharb.examplebackend.facades;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TodoFacade {
    TodoDTO createTodo(TodoDTO todo);

    Page<TodoDTO> getTodos(UUID userId, Pageable pageable);

    TodoDTO getTodo(Long id, UUID userId);
}
