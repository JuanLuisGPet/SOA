package com.demo.actapartamento.exceptions;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Manejador global centralizado de excepciones para toda la aplicación REST.
 *
 * <p>Esta clase intercepta todas las excepciones lanzadas en los controllers
 * y las convierte en respuestas HTTP estructuradas utilizando {@link ErrorResponse}.
 * Esto proporciona un manejo consistente de errores en toda la API.</p>
 *
 * <p>Excepciones manejadas y sus códigos HTTP correspondientes:</p>
 * <ul>
 *   <li><b>ResourceNotFoundException:</b> 404 Not Found - Recurso no encontrado</li>
 *   <li><b>BusinessRuleException:</b> 400 Bad Request - Violación de reglas de negocio</li>
 *   <li><b>ResourceAlreadyExistsException:</b> 409 Conflict - Recurso duplicado</li>
 *   <li><b>ReferentialIntegrityException:</b> 409 Conflict - Violación de integridad referencial</li>
 *   <li><b>MethodArgumentNotValidException:</b> 400 Bad Request - Errores de validación</li>
 *   <li><b>Exception:</b> 500 Internal Server Error - Cualquier otra excepción no controlada</li>
 * </ul>
 *
 * <p>La anotación {@code @RestControllerAdvice} permite que este manejador sea aplicado
 * globalmente a todos los controllers de la aplicación.</p>
 *
 * @see ErrorResponse
 * @see ResourceNotFoundException
 * @see BusinessRuleException
 * @see ResourceAlreadyExistsException
 * @see ReferentialIntegrityException
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ErrorResponse> handleBusinessRuleException(
            BusinessRuleException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Business Rule Violation",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Resource Already Exists",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(ReferentialIntegrityException.class)
    public ResponseEntity<ErrorResponse> handleReferentialIntegrityException(
            ReferentialIntegrityException ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Referential Integrity Violation",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        List<String> details = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.add(error.getField() + ": " + error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Los datos proporcionados no son válidos",
                request.getDescription(false).replace("uri=", ""),
                details
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex,
            WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ha ocurrido un error inesperado: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
