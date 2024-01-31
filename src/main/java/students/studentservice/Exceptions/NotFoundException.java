package students.studentservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
