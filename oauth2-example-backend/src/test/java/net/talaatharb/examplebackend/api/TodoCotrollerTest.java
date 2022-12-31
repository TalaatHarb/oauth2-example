package net.talaatharb.examplebackend.api;

import net.talaatharb.examplebackend.facades.TodoFacade;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TodoCotrollerTest {

    @InjectMocks
    private TodoRestController todoAPI;

    @Mock
    private TodoFacade todoFacade;

    @Test
    void testCreateTodoCallsCorrespondingFacade(){
        // Arrange
        var todo = TodoTestUtils.buildTodoDTOToCreate();

        // Act
        todoAPI.createTodo(todo);

        // Assert
        verify(todoFacade).createTodo(todo);
    }

    @Test
    void testCreateTodoGivesBadRequestWhenNoTitle(){
        // Arrange
        var todo = TodoTestUtils.buildTodoDTOToCreate();
        todo.setTitle("");

        // Act
        Executable action = () -> todoAPI.createTodo(todo);

        // Assert
        assertThrows(ResponseStatusException.class ,action, APIConstants.TODO_INVALID);
    }

    @Test
    void testGetTodosCallsCorrespondingFacade(){
        // Arrange
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending());

        todoAPI.getTodos(pageable);

        verify(todoFacade).getTodos(pageable);
    }

    @Test
    void testGetTodoCallsCorrespondingFacade(){
        // Arrange
        Long id = 1L;

        todoAPI.getTodo(id);

        verify(todoFacade).getTodo(id);
    }

}
