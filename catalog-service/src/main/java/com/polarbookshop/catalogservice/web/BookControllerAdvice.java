package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class BookControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    String bookNotFoundHandler(BookNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(BookAlreadyExistsException.class)
    String bookAlreadyExists(BookAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleRequestValidException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .collect(Collectors.toMap(error -> ((FieldError) error).getField(), objectError -> StringUtils.defaultIfBlank(objectError.getDefaultMessage(), "")));
    }
}