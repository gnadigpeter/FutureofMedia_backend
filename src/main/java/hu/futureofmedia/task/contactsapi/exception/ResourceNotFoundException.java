package hu.futureofmedia.task.contactsapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messages){
        super(messages);
    }

}
