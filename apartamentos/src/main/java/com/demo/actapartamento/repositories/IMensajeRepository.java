package com.demo.actapartamento.repositories;
import com.demo.actapartamento.models.MensajeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IMensajeRepository extends JpaRepository<MensajeModel, Integer> {
    List<MensajeModel> findByRemitenteId(Long remitenteId);
    List<MensajeModel> findByDestinatarioId(Long destinatarioId);
    List<MensajeModel> findByDestinatarioIdAndLeido(Long destinatarioId, Boolean leido);
        List<MensajeModel> findByReservacionIdReserva(Long reservacionId);
    @Query("SELECT m FROM MensajeModel m WHERE " +
           "(m.remitente.id = :clienteId1 AND m.destinatario.id = :clienteId2) OR " +
           "(m.remitente.id = :clienteId2 AND m.destinatario.id = :clienteId1) " +
           "ORDER BY m.fechaEnvio ASC")
    List<MensajeModel> findConversacionBetweenClientes(
            @Param("clienteId1") Long clienteId1,
            @Param("clienteId2") Long clienteId2
    );
}
