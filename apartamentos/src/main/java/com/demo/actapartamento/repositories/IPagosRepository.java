package com.demo.actapartamento.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.actapartamento.enums.EstadoPago;
import com.demo.actapartamento.enums.MetodoPago;
import com.demo.actapartamento.models.PagosModel;
@Repository
public interface IPagosRepository extends JpaRepository<PagosModel, Long> {
        @Query("SELECT p FROM PagosModel p WHERE p.reserva.idReserva = :reservaId")
    List<PagosModel> findByReservaId(@Param("reservaId") Long reservaId);
        @Query("SELECT p FROM PagosModel p WHERE p.estadoPago = :estadoPago")
    List<PagosModel> findByEstadoPago(@Param("estadoPago") EstadoPago estadoPago);
        @Query("SELECT p FROM PagosModel p WHERE p.metodoPago = :metodoPago")
    List<PagosModel> findByMetodoPago(@Param("metodoPago") MetodoPago metodoPago);
            @Query("SELECT p FROM PagosModel p WHERE p.reserva.idReserva = :reservaId AND p.estadoPago = :estadoPago")
    List<PagosModel> findByReservaIdAndEstadoPago(@Param("reservaId") Long reservaId, @Param("estadoPago") EstadoPago estadoPago);
}
