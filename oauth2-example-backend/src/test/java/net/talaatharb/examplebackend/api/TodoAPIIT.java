package net.talaatharb.examplebackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.talaatharb.examplebackend.AbstractControllerIT;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.repositories.TodoRepository;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TodoAPIIT extends AbstractControllerIT {

    public static final String USER_UUID = "e3782644-c072-4daf-a0f1-e2a080db7443";
    @Autowired
    TodoRepository todoRepository;

    @Test
    @WithMockUser(username = USER_UUID)
    void testCreateTodo() throws Exception {
        // Arrange
        TodoDTO todo = TodoTestUtils.buildTodoDTOToCreate();

        // Act
        var result = mockMVC.perform(post(APIConstants.TODOS_URL).accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todo)).contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @WithMockUser(username = USER_UUID)
    void testGetTodos() throws Exception {
        // Arrange
        // Page details
        String page = "0";
        String size = "10";
        String sort = "updateDate,desc";

        // Act
        var result = mockMVC.perform(get(APIConstants.TODOS_URL)
                .param("page", page)
                .param("size", size)
                .param("sort", sort)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk()).andExpect(jsonPath("$.totalPages").isNotEmpty());
    }

    @Test
    @WithMockUser(username = USER_UUID)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    void testGetTodo() throws Exception {
        // Arrange
        var todo = TodoTestUtils.buildTodoToCreate();
        todo.setUserId(UUID.fromString(USER_UUID));

        todo = todoRepository.saveAndFlush(todo);
        Integer id = todo.getId().intValue();
        String url = APIConstants.TODOS_URL + "/" + id;

        // Act
        var result = mockMVC.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk()).andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @WithMockUser(username = USER_UUID)
    void testGetTodoThrowsNotFoundExceptionWhenNotExist() throws Exception {
        // Arrange
        Integer id = 0;
        String url = APIConstants.TODOS_URL + "/" + id;

        // Act
        var result = mockMVC.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isNotFound());
    }
}
