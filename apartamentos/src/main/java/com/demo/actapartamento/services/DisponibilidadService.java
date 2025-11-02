package com.demo.actapartamento.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.models.DisponibilidadModel;
import com.demo.actapartamento.repositories.IDisponibilidadRepository;

@Service
public class DisponibilidadService {
    @Autowired
    private IDisponibilidadRepository disponibilidadRepository;

    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadRepository.findAll();
    }

    public Optional<DisponibilidadModel> getDisponibilidadById(Integer id) {
        return disponibilidadRepository.findById(id);
    }

    public DisponibilidadModel saveDisponibilidad(DisponibilidadModel disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public void deleteDisponibilidad(Integer id) {
        disponibilidadRepository.deleteById(id);
    }

    public Optional<DisponibilidadModel> updateDisponibilidad(Integer id, DisponibilidadModel disponibilidadDetails) {
        return disponibilidadRepository.findById(id)
                .map(disponibilidadToUpdate -> {
                    disponibilidadToUpdate.setPropiedad(disponibilidadDetails.getPropiedad());
                    disponibilidadToUpdate.setFecha(disponibilidadDetails.getFecha());
                    disponibilidadToUpdate.setDisponible(disponibilidadDetails.getDisponible());
                    disponibilidadToUpdate.setPrecioEspecial(disponibilidadDetails.getPrecioEspecial());
                    return disponibilidadRepository.save(disponibilidadToUpdate);
                });
    }

    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(Long propiedadId) {
                        return disponibilidadRepository.findByPropiedadIdPropiedad(propiedadId);
    }

    public List<DisponibilidadModel> getDisponibilidadesByPropiedadAndDisponible(Long propiedadId, Boolean disponible) {
                        return disponibilidadRepository.findByPropiedadIdPropiedadAndDisponible(propiedadId, disponible);
    }

    public List<DisponibilidadModel> getDisponibilidadesByFechaRange(LocalDate start, LocalDate end) {
        return disponibilidadRepository.findByFechaBetween(start, end);
    }

    public List<DisponibilidadModel> getDisponibilidadesByPropiedadAndFecha(Long propiedadId, LocalDate fecha) {
        return disponibilidadRepository.findByPropiedadAndFecha(propiedadId, fecha);
    }
}
