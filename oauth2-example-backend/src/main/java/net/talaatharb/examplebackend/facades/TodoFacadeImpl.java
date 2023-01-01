package net.talaatharb.examplebackend.facades;

import lombok.RequiredArgsConstructor;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.mappers.TodoMapper;
import net.talaatharb.examplebackend.services.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoFacadeImpl implements TodoFacade{

    private final TodoMapper todoMapper;

    private final TodoService todoService;
    @Override
    public TodoDTO createTodo(final TodoDTO todoDTO) {
        var todo = todoMapper.fromDTOToEntity(todoDTO);
        todo = todoService.createTodo(todo);
        return todoMapper.fromEntityToDTO(todo);
    }

    @Override
    public Page<TodoDTO> getTodos(final Pageable pageable) {
        final var todos = todoService.getTodos(pageable);
        return todoMapper.fromEntityToDTO(todos);
    }

    @Override
    public TodoDTO getTodo(final Long id) {
        final var todo = todoService.getTodo(id);
        return todoMapper.fromEntityToDTO(todo);
    }
}
