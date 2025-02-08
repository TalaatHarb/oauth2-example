package net.talaatharb.examplebackend.facades;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.mappers.TodoMapper;
import net.talaatharb.examplebackend.services.TodoService;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoFacadeImplTest {
    @InjectMocks
    private TodoFacadeImpl todoFacade;

    @Mock
    private TodoMapper todoMapper;

    @Mock
    private TodoService todoService;

    @Test
    void testCreateTodoCallsCorrespondingService(){
        // Arrange
        var todoDTOToCreate = TodoTestUtils.buildTodoDTOToCreate();
        var todo = TodoTestUtils.buildTodoToCreate();

        when(todoMapper.fromDTOToEntity(any(TodoDTO.class))).thenReturn(todo);
        when(todoService.createTodo(any(Todo.class))).thenReturn(todo);
        when(todoMapper.fromEntityToDTO(any(Todo.class))).thenReturn(todoDTOToCreate);

        // Act
        todoFacade.createTodo(todoDTOToCreate);

        // Assert
        verify(todoMapper).fromDTOToEntity(todoDTOToCreate);
        verify(todoService).createTodo(todo);
        verify(todoMapper).fromEntityToDTO(todo);
    }

    @Test
    void testGetTodosCallsCorrespondingService(){
        // Arrange
        // Page details
        int page = 0;
        int size = 10;
        UUID userId = UUID.randomUUID();
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending());

        Page<Todo> todos = Page.empty();
        when(todoService.getTodos(userId, pageable)).thenReturn(todos);
        when(todoMapper.fromEntityToDTO(todos)).thenReturn(Page.empty());

        // Act
        todoFacade.getTodos(userId, pageable);

        // Assert
        verify(todoService).getTodos(userId, pageable);
        verify(todoMapper).fromEntityToDTO(todos);
    }

    @Test
    void testGetTodoCallsCorrespondingService(){
        // Arrange
        // Page details
        Long id = 1L;
        var todo = new Todo();
        UUID userId = UUID.randomUUID();

        when(todoService.getTodo(id, userId)).thenReturn(todo);
        when(todoMapper.fromEntityToDTO(todo)).thenReturn(new TodoDTO());

        // Act
        todoFacade.getTodo(id, userId);

        // Assert
        verify(todoService).getTodo(id, userId);
        verify(todoMapper).fromEntityToDTO(todo);
    }
}
