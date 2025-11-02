package com.demo.actapartamento.exceptions;

/**
 * Excepción lanzada cuando se viola una regla de negocio de la aplicación.
 *
 * <p>Esta excepción se utiliza para indicar que una operación no puede completarse
 * debido a restricciones de lógica de negocio. Por ejemplo:</p>
 * <ul>
 *   <li>Intentar reservar una propiedad que no está disponible en las fechas solicitadas</li>
 *   <li>Realizar un pago por un monto inválido</li>
 *   <li>Crear una reseña sin tener una reserva completada</li>
 *   <li>Modificar datos que violan las políticas del sistema</li>
 * </ul>
 *
 * <p>El manejador global de excepciones retorna un HTTP 400 (Bad Request) cuando
 * se captura esta excepción.</p>
 *
 * @see GlobalExceptionHandler#handleBusinessRuleException
 */
public class BusinessRuleException extends RuntimeException {
    public BusinessRuleException(String message) {
        super(message);
    }
}
