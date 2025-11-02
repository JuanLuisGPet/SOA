package com.demo.actapartamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.enums.TipoPropiedad;
import com.demo.actapartamento.models.PropiedadModel;
import com.demo.actapartamento.repositories.IPropiedadRepository;

@Service
public class PropiedadService {
    @Autowired
    private IPropiedadRepository propiedadRepository;

    public List<PropiedadModel> getAllPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<PropiedadModel> getPropiedadById(Long id) {
        return propiedadRepository.findById(id);
    }

    public PropiedadModel savePropiedad(PropiedadModel propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public void deletePropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }

    public Optional<PropiedadModel> updatePropiedad(Long id, PropiedadModel propiedadDetails) {
        return propiedadRepository.findById(id)
                                .map(propiedadToUpdate -> {
                    propiedadToUpdate.setPropietario(propiedadDetails.getPropietario());
                    propiedadToUpdate.setTipo(propiedadDetails.getTipo());
                    propiedadToUpdate.setTitulo(propiedadDetails.getTitulo());
                    propiedadToUpdate.setDescripcion(propiedadDetails.getDescripcion());
                    propiedadToUpdate.setDireccion(propiedadDetails.getDireccion());
                    propiedadToUpdate.setCiudad(propiedadDetails.getCiudad());
                    propiedadToUpdate.setCodigoPostal(propiedadDetails.getCodigoPostal());
                    propiedadToUpdate.setPais(propiedadDetails.getPais());
                    propiedadToUpdate.setLatitud(propiedadDetails.getLatitud());
                    propiedadToUpdate.setLongitud(propiedadDetails.getLongitud());
                    propiedadToUpdate.setPrecioNoche(propiedadDetails.getPrecioNoche());
                    propiedadToUpdate.setCapacidad(propiedadDetails.getCapacidad());
                    propiedadToUpdate.setNumHabitaciones(propiedadDetails.getNumHabitaciones());
                    propiedadToUpdate.setNumBanos(propiedadDetails.getNumBanos());
                    propiedadToUpdate.setMetroCuadrados(propiedadDetails.getMetroCuadrados());
                    propiedadToUpdate.setComodidades(propiedadDetails.getComodidades());
                    propiedadToUpdate.setReglas(propiedadDetails.getReglas());
                    propiedadToUpdate.setEstadoPropiedad(propiedadDetails.getEstadoPropiedad());
                    return propiedadRepository.save(propiedadToUpdate);
                });
    }

    public List<PropiedadModel> getPropiedadesByTipo(TipoPropiedad tipo) {
        return propiedadRepository.findByTipo(tipo);
    }

    public List<PropiedadModel> getPropiedadesByPropietario(Long propietarioId) {
        return propiedadRepository.findByPropietarioId(propietarioId);
    }
}
