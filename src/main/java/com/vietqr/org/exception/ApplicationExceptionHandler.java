package com.vietqr.org.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import io.jsonwebtoken.SignatureException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    static final ResponseEntity<ResponseMessageDTO> responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(Status.FAILED, "E46"));

    // exception handler for validate request body
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageDTO> handleInvalidArgument(MethodArgumentNotValidException ex) {
        return responseEntity;
    }

    // exception handler for validate entity
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ResponseEntity<ResponseMessageDTO> handleConstraintViolationException(ConstraintViolationException ex) {
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SignatureException.class)
    ResponseEntity<ResponseMessageDTO> handleSignatureException(SignatureException ex) {
        return responseEntity;
    }

    // exception handler for validate field type
    // Example: int field but request param is string (abc)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseMessageDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return responseEntity;
    }

    // exception handler for validate json format
    // Example: { "abc": "abc" } -> { "abc": "abc }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseMessageDTO> handleJsonParseException(HttpMessageNotReadableException ex) {
        return responseEntity;
    }

    // exception handler for parse json error
    // Example: { "abc": 1 } -> { "abc": "abc" }
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ResponseMessageDTO> handleJsonParseException(JsonParseException ex) {
        return responseEntity;
    }

    // exception handler for validate other request body
    // Example: request param, request header
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ResponseMessageDTO> handleBindException(BindException ex) {
        return responseEntity;
    }
}
