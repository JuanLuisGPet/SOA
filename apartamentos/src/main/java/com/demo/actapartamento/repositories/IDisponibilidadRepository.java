package com.demo.actapartamento.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.actapartamento.models.DisponibilidadModel;

@Repository
public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Integer> {
            List<DisponibilidadModel> findByPropiedadIdPropiedad(Long propiedadId);

    List<DisponibilidadModel> findByFechaBetween(LocalDate start, LocalDate end);

            List<DisponibilidadModel> findByPropiedadIdPropiedadAndDisponible(Long propiedadId, Boolean disponible);

        @Query("SELECT d FROM DisponibilidadModel d WHERE d.propiedad.idPropiedad = :propiedadId AND d.fecha = :fecha")
    List<DisponibilidadModel> findByPropiedadAndFecha(@Param("propiedadId") Long propiedadId, @Param("fecha") LocalDate fecha);
}
