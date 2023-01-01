package net.talaatharb.examplebackend.services;

import net.talaatharb.examplebackend.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TodoService {

    Todo createTodo(Todo todo);

    Page<Todo> getTodos(UUID userId, Pageable pageable);

    Todo getTodo(Long anyLong, UUID userId);
}
