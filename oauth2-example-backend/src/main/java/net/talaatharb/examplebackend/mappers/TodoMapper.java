package net.talaatharb.examplebackend.mappers;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.entities.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface TodoMapper extends DefaultMapper<Todo, TodoDTO> {
}
