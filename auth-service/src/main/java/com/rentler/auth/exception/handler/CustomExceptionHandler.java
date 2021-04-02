package com.rentler.auth.exception.handler;

import com.rentler.auth.exception.exceptions.BadUsernameOrPasswordException;
import com.rentler.auth.exception.exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
@Getter
public class CustomExceptionHandler {

    private final ErrorAttributes errorAttributes;

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        return new HashMap<>(
                errorAttributes.getErrorAttributes(webRequest, true));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRuntimeException(
            RuntimeException exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<Object> handleApartmentNotFoundException(
            UserAlreadyExistsException exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BadUsernameOrPasswordException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(
            BadUsernameOrPasswordException exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(getErrorAttributes(request));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
