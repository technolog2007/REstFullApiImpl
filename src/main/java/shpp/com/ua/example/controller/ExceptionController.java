package shpp.com.ua.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody // Annotation that indicates a method return value should be bound to the web response body.
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String personNotFoundHandler(NotFoundException ex) {
        return ex.getMessage();
    }
}
