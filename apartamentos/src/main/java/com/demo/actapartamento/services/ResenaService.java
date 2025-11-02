package com.demo.actapartamento.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.models.ResenaModel;
import com.demo.actapartamento.repositories.IResenaRepository;
@Service
public class ResenaService {
    @Autowired
    private IResenaRepository resenaRepository;
    public List<ResenaModel> getAllResenas() {
        return resenaRepository.findAll();
    }
    public Optional<ResenaModel> getResenaById(Integer id) {
        return resenaRepository.findById(id);
    }
    public ResenaModel saveResena(ResenaModel resena) {
        return resenaRepository.save(resena);
    }
    public void deleteResena(Integer id) {
        resenaRepository.deleteById(id);
    }
    public Optional<ResenaModel> updateResena(Integer id, ResenaModel resenaDetails) {
        return resenaRepository.findById(id)
            .map(resenaToUpdate -> {
                resenaToUpdate.setReservacion(resenaDetails.getReservacion());
                resenaToUpdate.setCalificacionLimpieza(resenaDetails.getCalificacionLimpieza());
                resenaToUpdate.setCalificacionUbicacion(resenaDetails.getCalificacionUbicacion());
                resenaToUpdate.setCalificacionComunicacion(resenaDetails.getCalificacionComunicacion());
                resenaToUpdate.setCalificacionGeneral(resenaDetails.getCalificacionGeneral());
                resenaToUpdate.setComentario(resenaDetails.getComentario());
                resenaToUpdate.setRespuestaPropietario(resenaDetails.getRespuestaPropietario());
                return resenaRepository.save(resenaToUpdate);
            });
    }
    public Optional<ResenaModel> agregarRespuestaPropietario(Integer id, String respuesta) {
        return resenaRepository.findById(id)
            .map(resena -> {
                resena.setRespuestaPropietario(respuesta);
                return resenaRepository.save(resena);
            });
    }
        public List<ResenaModel> getResenasByReservacion(Long reservacionId) {
        return resenaRepository.findByReservacionIdReserva(reservacionId);
    }
    public List<ResenaModel> getResenasByPropiedad(Long propiedadId) {
        return resenaRepository.findByPropiedadId(propiedadId);
    }
    public List<ResenaModel> getResenasByCliente(Long clienteId) {
        return resenaRepository.findByClienteId(clienteId);
    }
    public List<ResenaModel> getResenasWithRespuesta() {
        return resenaRepository.findResenasWithRespuestaPropietario();
    }
}
