package com.demo.actapartamento.repositories;
import com.demo.actapartamento.models.ResenaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IResenaRepository extends JpaRepository<ResenaModel, Integer> {
    @Query("SELECT r FROM ResenaModel r WHERE r.reservacion.idReserva = :reservacionId")
    List<ResenaModel> findByReservacionIdReserva(@Param("reservacionId") Long reservacionId);
        @Query("SELECT r FROM ResenaModel r WHERE r.reservacion.propiedad.idPropiedad = :propiedadId")
    List<ResenaModel> findByPropiedadId(@Param("propiedadId") Long propiedadId);
        @Query("SELECT r FROM ResenaModel r WHERE r.reservacion.cliente.id = :clienteId")
    List<ResenaModel> findByClienteId(@Param("clienteId") Long clienteId);
    @Query("SELECT r FROM ResenaModel r WHERE r.respuestaPropietario IS NOT NULL")
    List<ResenaModel> findResenasWithRespuestaPropietario();
}
