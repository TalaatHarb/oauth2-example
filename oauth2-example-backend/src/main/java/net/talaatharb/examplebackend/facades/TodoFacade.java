package net.talaatharb.examplebackend.facades;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoFacade {
    TodoDTO createTodo(TodoDTO todo);

    Page<TodoDTO> getTodos(Pageable pageable);

    TodoDTO getTodo(Long id);
}
