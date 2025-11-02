package com.demo.actapartamento.exceptions;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase DTO (Data Transfer Object) para estructurar las respuestas de error de la API REST.
 *
 * <p>Esta clase proporciona un formato estandarizado y consistente para todas las respuestas
 * de error de la aplicación, facilitando el manejo de errores en el cliente.</p>
 *
 * <p>Campos incluidos en la respuesta:</p>
 * <ul>
 *   <li><b>timestamp:</b> Momento exacto en que ocurrió el error</li>
 *   <li><b>status:</b> Código de estado HTTP (404, 400, 409, 500, etc.)</li>
 *   <li><b>error:</b> Tipo de error (Not Found, Bad Request, Conflict, etc.)</li>
 *   <li><b>message:</b> Mensaje descriptivo del error para el usuario</li>
 *   <li><b>path:</b> Ruta del endpoint donde ocurrió el error</li>
 *   <li><b>details:</b> Lista opcional con detalles adicionales (usado principalmente para errores de validación)</li>
 * </ul>
 *
 * <p>Esta clase es utilizada por el {@link GlobalExceptionHandler} para construir
 * todas las respuestas de error de la aplicación.</p>
 *
 * @see GlobalExceptionHandler
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<String> details;
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
