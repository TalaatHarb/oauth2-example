package net.talaatharb.examplebackend.services;


import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.exceptions.TodoNotFoundException;
import net.talaatharb.examplebackend.repositories.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(final Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Page<Todo> getTodos(final UUID userId, final Pageable pageable) {
        return todoRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Todo getTodo(final Long id, UUID userId) {
        final Optional<Todo> todoOptional = todoRepository.findByIdAndUserId(id, userId);
        if(todoOptional.isEmpty()){
            throw new TodoNotFoundException(id);
        }
        return todoOptional.get();
    }
}
