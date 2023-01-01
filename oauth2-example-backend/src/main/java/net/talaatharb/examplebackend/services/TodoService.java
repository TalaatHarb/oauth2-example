package net.talaatharb.examplebackend.services;

import net.talaatharb.examplebackend.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoService {

    Todo createTodo(Todo todo);

    Page<Todo> getTodos(Pageable pageable);

    Todo getTodo(Long anyLong);
}
