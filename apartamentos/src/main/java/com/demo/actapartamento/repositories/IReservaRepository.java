package com.demo.actapartamento.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.actapartamento.enums.EstadoReserva;
import com.demo.actapartamento.models.ReservaModel;
@Repository
public interface IReservaRepository extends JpaRepository<ReservaModel, Long> {
    List<ReservaModel> findByClienteId(Long clienteId);
        @Query("SELECT r FROM ReservaModel r WHERE r.propiedad.idPropiedad = :propiedadId")
    List<ReservaModel> findByPropiedadId(@Param("propiedadId") Long propiedadId);
    List<ReservaModel> findByEstado(EstadoReserva estado);
    List<ReservaModel> findByClienteIdAndEstado(Long clienteId, EstadoReserva estado);
}
