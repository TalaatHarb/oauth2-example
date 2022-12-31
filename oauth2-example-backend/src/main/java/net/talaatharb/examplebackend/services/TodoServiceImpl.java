package net.talaatharb.examplebackend.services;


import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.repositories.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }
}
