package net.talaatharb.examplebackend.utils;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.entities.Todo;

public class TodoTestUtils {
    public static TodoDTO buildTodoDTOToCreate(){
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setTitle("Todo");
        todoDTO.setDescription("Todo Description");

        return todoDTO;
    }

    public static Todo buildTodoToCreate() {
        Todo todo = new Todo();
        todo.setTitle("Todo");
        todo.setDescription("Todo Description");

        return todo;
    }
}
