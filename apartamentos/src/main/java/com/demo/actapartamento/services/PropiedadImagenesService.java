package com.demo.actapartamento.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.actapartamento.models.PropiedadImagenesModel;
import com.demo.actapartamento.repositories.IPropiedadImagenesRepository;
@Service
public class PropiedadImagenesService {
    @Autowired
    private IPropiedadImagenesRepository propiedadImagenesRepository;
    public List<PropiedadImagenesModel> getAllImagenes() {
        return propiedadImagenesRepository.findAll();
    }
    public Optional<PropiedadImagenesModel> getImagenById(Integer id) {
        return propiedadImagenesRepository.findById(id);
    }
    public PropiedadImagenesModel saveImagen(PropiedadImagenesModel imagen) {
        return propiedadImagenesRepository.save(imagen);
    }
    public void deleteImagen(Integer id) {
        propiedadImagenesRepository.deleteById(id);
    }
    public Optional<PropiedadImagenesModel> updateImagen(Integer id, PropiedadImagenesModel imagenDetails) {
        return propiedadImagenesRepository.findById(id)
            .map(imagenToUpdate -> {
                imagenToUpdate.setPropiedad(imagenDetails.getPropiedad());
                imagenToUpdate.setUrlImagen(imagenDetails.getUrlImagen());
                imagenToUpdate.setOrden(imagenDetails.getOrden());
                imagenToUpdate.setEsPrincipal(imagenDetails.getEsPrincipal());
                return propiedadImagenesRepository.save(imagenToUpdate);
            });
    }
    public List<PropiedadImagenesModel> getImagenesByPropiedad(Long propiedadId) {
                        return propiedadImagenesRepository.findByPropiedadIdPropiedadOrderByOrdenAsc(propiedadId);
    }
    public Optional<PropiedadImagenesModel> getImagenPrincipalByPropiedad(Long propiedadId) {
                        return propiedadImagenesRepository.findByPropiedadIdPropiedadAndEsPrincipal(propiedadId, true);
    }
}
