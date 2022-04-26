package springseller.filipey.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springseller.filipey.api.ApiErrors;
import springseller.filipey.api.exception.ObjectNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleObjectNotFoundException(ObjectNotFoundException exception) {
        String errorMessage = exception.getMessage();

        return new ApiErrors(errorMessage);
    }
}
