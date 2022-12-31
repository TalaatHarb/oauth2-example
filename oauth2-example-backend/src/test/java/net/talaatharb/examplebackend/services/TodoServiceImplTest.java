package net.talaatharb.examplebackend.services;

import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.repositories.TodoRepository;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {
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
}