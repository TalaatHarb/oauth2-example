package net.talaatharb.examplebackend.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import net.talaatharb.examplebackend.dtos.TodoDTO;
import net.talaatharb.examplebackend.entities.Todo;
import net.talaatharb.examplebackend.utils.TodoTestUtils;
import org.mapstruct.factory.Mappers;


class TodoMapperTest implements DefaultMapperTest<Todo, TodoDTO> {

	private TodoMapper todoMapper = Mappers.getMapper(TodoMapper.class);

	@Override
	public void assertEqualEntityAndDto(Todo todo, TodoDTO todoDTO) {
		assertEquals(todoDTO.getId(), todo.getId());
		assertEquals(todoDTO.getTitle(), todo.getTitle());
	}

	@Override
	public TodoDTO createDTO() {
		TodoDTO todoDTO = TodoTestUtils.buildTodoDTOToCreate();
		todoDTO.setId(1L);
		return todoDTO;
	}

	@Override
	public Todo createEntity() {
		Todo todo = TodoTestUtils.buildTodoToCreate();
		todo.setId(1L);
		return todo;
	}

	@Override
	public DefaultMapper<Todo, TodoDTO> getMapper() {
		return todoMapper;
	}
}
