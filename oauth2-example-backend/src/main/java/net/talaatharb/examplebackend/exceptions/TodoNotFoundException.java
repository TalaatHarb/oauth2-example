package net.talaatharb.examplebackend.exceptions;

public class TodoNotFoundException extends RuntimeException{
    private Long id;
    public TodoNotFoundException(Long id){
        super();
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "todo.not-found." + id;
    }
}
