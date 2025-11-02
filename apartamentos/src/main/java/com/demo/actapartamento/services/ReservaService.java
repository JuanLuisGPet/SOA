package com.demo.actapartamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.enums.EstadoReserva;
import com.demo.actapartamento.models.ReservaModel;
import com.demo.actapartamento.repositories.IReservaRepository;

@Service
public class ReservaService {
    @Autowired
    private IReservaRepository reservaRepository;

    public List<ReservaModel> getAllReservas() {
        return reservaRepository.findAll();
    }

    public Optional<ReservaModel> getReservaById(Long id) {
        return reservaRepository.findById(id);
    }

    public ReservaModel saveReserva(ReservaModel reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    public Optional<ReservaModel> updateReserva(Long id, ReservaModel reservaDetails) {
        return reservaRepository.findById(id)
                                .map(reservaToUpdate -> {
                    reservaToUpdate.setCliente(reservaDetails.getCliente());
                    reservaToUpdate.setPropiedad(reservaDetails.getPropiedad());
                    reservaToUpdate.setFechaInicio(reservaDetails.getFechaInicio());
                    reservaToUpdate.setFechaFin(reservaDetails.getFechaFin());
                    reservaToUpdate.setNumHuespedes(reservaDetails.getNumHuespedes());
                    reservaToUpdate.setPrecioTotal(reservaDetails.getPrecioTotal());
                    reservaToUpdate.setEstado(reservaDetails.getEstado());
                    reservaToUpdate.setObservaciones(reservaDetails.getObservaciones());
                    return reservaRepository.save(reservaToUpdate);
                });
    }

    public List<ReservaModel> getReservasByCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public List<ReservaModel> getReservasByPropiedad(Long propiedadId) {
        return reservaRepository.findByPropiedadId(propiedadId);
    }

    public List<ReservaModel> getReservasByEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<ReservaModel> getReservasByClienteAndEstado(Long clienteId, EstadoReserva estado) {
        return reservaRepository.findByClienteIdAndEstado(clienteId, estado);
    }
}
