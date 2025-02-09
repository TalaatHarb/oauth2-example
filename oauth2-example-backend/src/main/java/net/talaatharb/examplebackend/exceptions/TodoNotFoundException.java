package net.talaatharb.examplebackend.exceptions;

public class TodoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -5438967927948050390L;
	
	private final Long id;
    public TodoNotFoundException(final Long id){
        super();
        this.id = id;
    }

    @Override
    public String getMessage(){
        return "todo.not-found." + id;
    }
}
