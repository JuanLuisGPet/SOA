package com.demo.actapartamento.exceptions;

/**
 * Excepción lanzada cuando se intenta crear un recurso que ya existe en la base de datos.
 *
 * <p>Esta excepción se utiliza para prevenir duplicados y mantener la integridad de datos únicos.
 * Por ejemplo:</p>
 * <ul>
 *   <li>Registrar un cliente con un email que ya está en uso</li>
 *   <li>Crear una propiedad con un código de referencia duplicado</li>
 *   <li>Intentar insertar cualquier entidad con campos únicos que ya existen</li>
 *   <li>Violación de restricciones de unicidad en la base de datos</li>
 * </ul>
 *
 * <p>Proporciona dos constructores para personalizar el mensaje de error:</p>
 * <ul>
 *   <li>Constructor simple con mensaje personalizado</li>
 *   <li>Constructor con formato estándar (nombre del recurso, campo y valor duplicado)</li>
 * </ul>
 *
 * <p>El manejador global de excepciones retorna un HTTP 409 (Conflict) cuando
 * se captura esta excepción.</p>
 *
 * @see GlobalExceptionHandler#handleResourceAlreadyExistsException
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
    public ResourceAlreadyExistsException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s ya existe con %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
