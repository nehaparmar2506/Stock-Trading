package com.hackerrank.stocktrades.validation;

import com.hackerrank.stocktrades.exception.TradeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice()
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the exception for any request if the input parameter is not valid and throws proper
     * response message with error code.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(exception.getClass().getName());

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errorObj = exception.getBindingResult()
                .getGlobalErrors()
                .stream()
                .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        errors.addAll(errorObj);

        return handleExceptionInternal(exception, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handles TradeNotFoundException and returns proper response.
     * @param exception Custom exception for Trade not found.
     * @param request Request
     * @return Response Entity
     */
    @ExceptionHandler( TradeNotFoundException.class )
    public ResponseEntity<Object> handleTradeNotFoundException(TradeNotFoundException exception,
                                                               WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exception occurred! " + ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
