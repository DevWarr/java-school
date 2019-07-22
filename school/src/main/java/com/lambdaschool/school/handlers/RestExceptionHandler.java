package com.lambdaschool.school.handlers;

import com.lambdaschool.school.exceptions.ResourceNotFoundException;
import com.lambdaschool.school.model.ErrorDetail;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

// When a Controller returns a ResponseEntity, this Advice annotation takes it and works with it
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    // The exceptions we'll catch: A "ResourceNotFoundException" and a "UsernameNotFoundException"
    // Because these annotations are for Spring, we use .class to recognize the files after they've been compiled
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request)
    {
        // Create our ErrorDetail that we'll send back
        ErrorDetail errorDetail = new ErrorDetail();

        // Common errorDetail requirements
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");

        // Extra things that can vary
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    // If our user requests something that is the wrong type (String instead of int for example), this will handle this error
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        // Common requirements
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());

        // Extra things that vary
        errorDetail.setTitle(ex.getPropertyName());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(request.getDescription(true));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    // What happens if they go to an endpoint that just doesn't exist?
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());

        errorDetail.setTitle(ex.getRequestURL());
        errorDetail.setDetail(request.getDescription(true));
        errorDetail.setDeveloperMessage("Rest Handler Not Found (Check for Valid URL)");

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    // What happens if they go to the correct endpoint, but they're using the wrong method? (POST instead of GET, for example)

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());

        errorDetail.setTitle(ex.getMethod());
        errorDetail.setDetail(request.getDescription(true));
        errorDetail.setDeveloperMessage("HTTP Method not Valid for Endpoint (check for Valid URI and Proper HTTP Method)");

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
