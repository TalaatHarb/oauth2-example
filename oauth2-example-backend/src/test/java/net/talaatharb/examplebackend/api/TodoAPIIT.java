package net.talaatharb.examplebackend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.talaatharb.examplebackend.AbstractControllerIT;
import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoAPIIT extends AbstractControllerIT {

    @Test
    void testCreateTodo() throws Exception {
        // Arrange
        TodoDTO todo = TodoTestUtils.buildTodoDTOToCreate();

        // Act
        var result = mockMVC.perform(post(APIConstants.TODOS_URL).accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todo)).contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
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
}
