package com.demo.actapartamento.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo.actapartamento.models.PropiedadImagenesModel;
import com.demo.actapartamento.services.PropiedadImagenesService;
@RestController
@RequestMapping("/api/imagenes")
public class PropiedadImagenesController {
    @Autowired
    private PropiedadImagenesService propiedadImagenesService;
    @GetMapping
    public List<PropiedadImagenesModel> getAllImagenes() {
        return propiedadImagenesService.getAllImagenes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PropiedadImagenesModel> getImagenById(@PathVariable Integer id) {
        Optional<PropiedadImagenesModel> imagen = propiedadImagenesService.getImagenById(id);
        return imagen.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/propiedad/{id}")
    public List<PropiedadImagenesModel> getImagenesByPropiedad(@PathVariable Long id) {
        return propiedadImagenesService.getImagenesByPropiedad(id);
    }
    @GetMapping("/propiedad/{id}/principal")
    public ResponseEntity<PropiedadImagenesModel> getImagenPrincipal(@PathVariable Long id) {
        Optional<PropiedadImagenesModel> imagen = propiedadImagenesService.getImagenPrincipalByPropiedad(id);
        return imagen.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public PropiedadImagenesModel createImagen(@RequestBody PropiedadImagenesModel imagen) {
        return propiedadImagenesService.saveImagen(imagen);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PropiedadImagenesModel> updateImagen(
            @PathVariable Integer id,
            @RequestBody PropiedadImagenesModel imagenDetails) {
        return propiedadImagenesService.updateImagen(id, imagenDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Integer id) {
        Optional<PropiedadImagenesModel> imagen = propiedadImagenesService.getImagenById(id);
        if (imagen.isPresent()) {
            propiedadImagenesService.deleteImagen(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
