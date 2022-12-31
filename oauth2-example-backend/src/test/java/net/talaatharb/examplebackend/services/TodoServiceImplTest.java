package net.talaatharb.examplebackend.services;

import net.talaatharb.examplebackend.repositories.TodoRepository;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {
    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;
    @Test
    void createTodoCallsCorrespondingRepositority(){
        // Arrange
        var todo = TodoTestUtils.buildTodoToCreate();

        // Act
        todoService.createTodo(todo);

        // Assert
        verify(todoRepository).save(todo);
    }
}
