package net.talaatharb.examplebackend.services;

import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.exceptions.TodoNotFoundException;
import net.talaatharb.examplebackend.repositories.TodoRepository;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {
    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;
    @Test
    void testCreateTodoCallsCorrespondingRepositority(){
        // Arrange
        var todo = TodoTestUtils.buildTodoToCreate();

        // Act
        todoService.createTodo(todo);

        // Assert
        verify(todoRepository).save(todo);
    }

    @Test
    void testGetTodosCallsCorrespondingRepository(){
        // Arrange
        // Page details
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending());

        Page<Todo> todos = Page.empty();
        when(todoRepository.findAll(pageable)).thenReturn(todos);

        // Act
        todoService.getTodos(pageable);

        // Assert
        verify(todoRepository).findAll(pageable);
    }

    @Test
    void testGetTodoCallsCorrespondingRepository(){
        // Arrange
        // Page details
        Long id = 1L;
        when(todoRepository.findById(anyLong())).thenReturn(Optional.of(new Todo()));

        // Act
        todoService.getTodo(id);

        // Assert
        verify(todoRepository).findById(id);
    }

    @Test
    void testGetTodoThrowsNotFoundIfNotExist(){
        // Arrange
        // Page details
        Long id = 1L;
        when(todoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act
        Executable action = () -> todoService.getTodo(id);

        // Assert
        assertThrows(TodoNotFoundException.class, action);
    }
}
