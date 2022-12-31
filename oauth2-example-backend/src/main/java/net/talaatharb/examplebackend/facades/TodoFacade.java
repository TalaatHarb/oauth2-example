package net.talaatharb.examplebackend.facades;

import net.talaatharb.examplebackend.dtos.TodoDTO;

public interface TodoFacade {
    public abstract TodoDTO createTodo(TodoDTO todo);
}
