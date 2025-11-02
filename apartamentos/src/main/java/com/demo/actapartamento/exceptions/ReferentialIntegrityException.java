package com.demo.actapartamento.exceptions;

/**
 * Excepción lanzada cuando se viola la integridad referencial entre entidades.
 *
 * <p>Esta excepción se utiliza para proteger las relaciones entre entidades
 * e impedir operaciones que comprometerían la consistencia de los datos.
 * Por ejemplo:</p>
 * <ul>
 *   <li>Intentar eliminar un cliente que tiene reservas activas</li>
 *   <li>Borrar una propiedad que tiene reservas asociadas</li>
 *   <li>Eliminar una reserva que tiene pagos registrados</li>
 *   <li>Realizar operaciones que rompan las relaciones de clave foránea</li>
 * </ul>
 *
 * <p>Esta excepción ayuda a mantener la integridad de las relaciones en cascada
 * y previene la creación de datos huérfanos en la base de datos.</p>
 *
 * <p>El manejador global de excepciones retorna un HTTP 409 (Conflict) cuando
 * se captura esta excepción.</p>
 *
 * @see GlobalExceptionHandler#handleReferentialIntegrityException
 */
public class ReferentialIntegrityException extends RuntimeException {
    public ReferentialIntegrityException(String message) {
        super(message);
    }
}
