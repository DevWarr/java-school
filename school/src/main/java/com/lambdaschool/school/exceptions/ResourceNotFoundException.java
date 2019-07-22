package com.lambdaschool.school.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
    // Constructor
    public ResourceNotFoundException(String message)
    {
        // The RuntimeException class takes in a message, so we pass our message up
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause)
    {
        // RuntimeExceptino can also take a cause, so we add a separate constructor in case we get both things
        super(message, cause);
    }
}
