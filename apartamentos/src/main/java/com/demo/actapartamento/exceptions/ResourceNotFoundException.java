package com.demo.actapartamento.exceptions;

/**
 * Excepción lanzada cuando no se encuentra un recurso solicitado en la base de datos.
 *
 * <p>Esta excepción se utiliza para indicar que un recurso específico no existe.
 * Por ejemplo:</p>
 * <ul>
 *   <li>Buscar un cliente por ID que no existe</li>
 *   <li>Intentar actualizar una propiedad que no se encuentra</li>
 *   <li>Consultar una reserva con un identificador inexistente</li>
 *   <li>Acceder a cualquier entidad que no está registrada en el sistema</li>
 * </ul>
 *
 * <p>Proporciona dos constructores para personalizar el mensaje de error:</p>
 * <ul>
 *   <li>Constructor simple con mensaje personalizado</li>
 *   <li>Constructor con formato estándar (nombre del recurso, campo y valor buscado)</li>
 * </ul>
 *
 * <p>El manejador global de excepciones retorna un HTTP 404 (Not Found) cuando
 * se captura esta excepción.</p>
 *
 * @see GlobalExceptionHandler#handleResourceNotFoundException
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado con %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
