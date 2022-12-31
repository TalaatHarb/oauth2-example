package net.talaatharb.examplebackend.services;


import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.exceptions.TodoNotFoundException;
import net.talaatharb.examplebackend.repositories.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Page<Todo> getTodos(Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    @Override
    public Todo getTodo(long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if(todoOptional.isEmpty()){
            throw new TodoNotFoundException(id);
        }
        return todoOptional.get();
    }
}
