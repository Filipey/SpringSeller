package springseller.filipey.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springseller.filipey.api.ApiErrors;
import springseller.filipey.api.exception.ClientNotFoundException;
import springseller.filipey.api.exception.ObjectNotFoundException;
import springseller.filipey.api.exception.RequestNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleObjectNotFoundException(ObjectNotFoundException exception) {
        String errorMessage = exception.getMessage();

        return new ApiErrors(errorMessage);
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleRequestNotFoundException(RequestNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleClientNotFoundException(ClientNotFoundException exception) {
        return new ApiErrors(exception.getMessage());
    }
}
