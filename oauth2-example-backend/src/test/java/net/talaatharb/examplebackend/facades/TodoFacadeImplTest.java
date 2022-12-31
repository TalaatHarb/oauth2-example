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
}
