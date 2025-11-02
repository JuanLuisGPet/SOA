package com.demo.actapartamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.models.MensajeModel;
import com.demo.actapartamento.repositories.IMensajeRepository;

@Service
public class MensajeService {
    @Autowired
    private IMensajeRepository mensajeRepository;

    public List<MensajeModel> getAllMensajes() {
        return mensajeRepository.findAll();
    }

    public Optional<MensajeModel> getMensajeById(Integer id) {
        return mensajeRepository.findById(id);
    }

    public MensajeModel saveMensaje(MensajeModel mensaje) {
        return mensajeRepository.save(mensaje);
    }

    public void deleteMensaje(Integer id) {
        mensajeRepository.deleteById(id);
    }

    public Optional<MensajeModel> updateMensaje(Integer id, MensajeModel mensajeDetails) {
        return mensajeRepository.findById(id)
                .map(mensajeToUpdate -> {
                    mensajeToUpdate.setRemitente(mensajeDetails.getRemitente());
                    mensajeToUpdate.setDestinatario(mensajeDetails.getDestinatario());
                    mensajeToUpdate.setReservacion(mensajeDetails.getReservacion());
                    mensajeToUpdate.setAsunto(mensajeDetails.getAsunto());
                    mensajeToUpdate.setContenido(mensajeDetails.getContenido());
                    mensajeToUpdate.setLeido(mensajeDetails.getLeido());
                    return mensajeRepository.save(mensajeToUpdate);
                });
    }

    public List<MensajeModel> getMensajesEnviados(Long clienteId) {
        return mensajeRepository.findByRemitenteId(clienteId);
    }

    public List<MensajeModel> getMensajesRecibidos(Long clienteId) {
        return mensajeRepository.findByDestinatarioId(clienteId);
    }

    public List<MensajeModel> getMensajesNoLeidos(Long clienteId) {
        return mensajeRepository.findByDestinatarioIdAndLeido(clienteId, false);
    }

        public List<MensajeModel> getMensajesByReservacion(Long reservacionId) {
        return mensajeRepository.findByReservacionIdReserva(reservacionId);
    }

    public List<MensajeModel> getConversacion(Long clienteId1, Long clienteId2) {
        return mensajeRepository.findConversacionBetweenClientes(clienteId1, clienteId2);
    }

    public Optional<MensajeModel> marcarComoLeido(Integer id) {
        return mensajeRepository.findById(id)
                .map(mensaje -> {
                    mensaje.setLeido(true);
                    return mensajeRepository.save(mensaje);
                });
    }

    public void marcarTodosComoLeidos(Long clienteId) {
        List<MensajeModel> mensajesNoLeidos = getMensajesNoLeidos(clienteId);
        mensajesNoLeidos.forEach(mensaje -> {
            mensaje.setLeido(true);
            mensajeRepository.save(mensaje);
        });
    }
}
