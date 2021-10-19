package ru.tyreservice.aggregator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tyreservice.aggregator.dto.responses.StatusResponse;
import ru.tyreservice.aggregator.exceptions.NotFoundException;
import ru.tyreservice.aggregator.exceptions.NullValueFieldException;

import java.util.Collections;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StatusResponse handleNotFoundException(NotFoundException e) {
        return StatusResponse.builder().success(false).errors(Collections.singletonList(e.getError())).build();
    }

    @ExceptionHandler(value = NullValueFieldException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public StatusResponse handleNullValueFieldException(NullValueFieldException e) {
        return StatusResponse.builder().success(false).errors(e.getErrors()).build();
    }
}
