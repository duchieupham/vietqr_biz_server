package com.vietqr.org.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.vietqr.org.constant.Status;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
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

import java.nio.file.AccessDeniedException;

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

    // exception handler for Token has expired
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseMessageDTO> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessageDTO(Status.FAILED, "E199"));
    }

    // exception handler for Invalid signature
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ResponseMessageDTO> handleSignatureException(SignatureException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessageDTO(Status.FAILED, "E200"));
    }

    // exception handler for Invalid JWT token
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ResponseMessageDTO> handleMalformedJwtException(MalformedJwtException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(Status.FAILED, "E201"));
    }

    // exception handler for Unsupported JWT
    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ResponseMessageDTO> handleUnsupportedJwtException(UnsupportedJwtException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(Status.FAILED, "E202"));
    }

    // exception handler for Token is null or empty
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseMessageDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessageDTO(Status.FAILED, "E203"));
    }

    // exception handler for Not have the necessary permissions
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessageDTO> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessageDTO(Status.FAILED, "E204"));
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
