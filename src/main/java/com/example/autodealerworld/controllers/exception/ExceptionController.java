package com.example.autodealerworld.controllers.exception;

import com.example.autodealerworld.entity.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDTO>> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<ExceptionDTO> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ExceptionDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException ex){
        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadWordsFoundException.class)
    public ResponseEntity<String> handleBadWordsException(BadWordsFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
