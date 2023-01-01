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
import org.springframework.security.core.Authentication;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoCotrollerTest {

    @InjectMocks
    private TodoRestController todoAPI;

    @Mock
    private TodoFacade todoFacade;

    @Mock
    private Authentication authDetails;

    @Test
    void testCreateTodoCallsCorrespondingFacade(){
        // Arrange
        var todo = TodoTestUtils.buildTodoDTOToCreate();
        UUID userId = UUID.randomUUID();

        when(authDetails.getName()).thenReturn(userId.toString());

        // Act
        todoAPI.createTodo(todo, authDetails);

        // Assert
        verify(authDetails).getName();
        verify(todoFacade).createTodo(todo);
    }

    @Test
    void testCreateTodoGivesBadRequestWhenNoTitle(){
        // Arrange
        var todo = TodoTestUtils.buildTodoDTOToCreate();
        todo.setTitle("");

        // Act
        Executable action = () -> todoAPI.createTodo(todo, authDetails);

        // Assert
        assertThrows(ResponseStatusException.class ,action, APIConstants.TODO_INVALID);
    }

    @Test
    void testGetTodosCallsCorrespondingFacade(){
        // Arrange
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("updateDate").descending());
        UUID userId = UUID.randomUUID();

        when(authDetails.getName()).thenReturn(userId.toString());

        todoAPI.getTodos(pageable, authDetails);

        verify(todoFacade).getTodos(userId, pageable);
    }

    @Test
    void testGetTodoCallsCorrespondingFacade(){
        // Arrange
        Long id = 1L;
        UUID userId = UUID.randomUUID();
        when(authDetails.getName()).thenReturn(userId.toString());

        // Act
        todoAPI.getTodo(id, authDetails);

        // Assert
        verify(authDetails).getName();
        verify(todoFacade).getTodo(id, userId);
    }

}
