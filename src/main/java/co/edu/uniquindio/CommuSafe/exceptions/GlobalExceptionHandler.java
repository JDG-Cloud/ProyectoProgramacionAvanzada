package co.edu.uniquindio.CommuSafe.exceptions;

import co.edu.uniquindio.CommuSafe.exceptions.model.ErrorResponse;
import co.edu.uniquindio.CommuSafe.modules.category.ResourceNotFoundException;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatus().value(), ex.getStatus().toString(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Usando el constructor con (int status, String description, String message)
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),  // 404 como int
                "NOT_FOUND",                   // descripción como String
                ex.getMessage()                // mensaje de error como String
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),  // 400 como int
                "VALIDATION_ERROR",              // descripción como String
                ex.getMessage()                  // mensaje de error como String
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),   // 403 como int
                "ACCESS_DENIED",                // descripción como String
                "No tienes permiso para realizar esta operación"  // mensaje de error como String
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Construimos un mensaje que incluya los detalles de los errores
        StringBuilder detailedMessage = new StringBuilder("Error en la validación de campos: ");
        errors.forEach((field, message) ->
                detailedMessage.append(field).append(": ").append(message).append("; ")
        );

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),   // 400 como int
                "VALIDATION_ERROR",               // descripción como String
                detailedMessage.toString()        // mensaje que incluye todos los errores de validación
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}


