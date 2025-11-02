package com.demo.actapartamento.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.actapartamento.models.DisponibilidadModel;
import com.demo.actapartamento.services.DisponibilidadService;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {
    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping
    public List<DisponibilidadModel> getAllDisponibilidades() {
        return disponibilidadService.getAllDisponibilidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> getDisponibilidadById(@PathVariable Integer id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        return disponibilidad.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/propiedad/{id}")
    public List<DisponibilidadModel> getDisponibilidadesByPropiedad(@PathVariable Long id) {
        return disponibilidadService.getDisponibilidadesByPropiedad(id);
    }

    @GetMapping("/propiedad/{id}/libre")
    public List<DisponibilidadModel> getDisponibilidadesByPropiedadAndDisponible(
            @PathVariable Long id,
            @RequestParam(defaultValue = "true") Boolean disponible) {
        return disponibilidadService.getDisponibilidadesByPropiedadAndDisponible(id, disponible);
    }

    @GetMapping("/rango")
    public List<DisponibilidadModel> getDisponibilidadesByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return disponibilidadService.getDisponibilidadesByFechaRange(start, end);
    }

    @GetMapping("/propiedad/{id}/fecha")
    public List<DisponibilidadModel> getDisponibilidadesByPropiedadAndFecha(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return disponibilidadService.getDisponibilidadesByPropiedadAndFecha(id, fecha);
    }

    @PostMapping
    public DisponibilidadModel createDisponibilidad(@RequestBody DisponibilidadModel disponibilidad) {
        return disponibilidadService.saveDisponibilidad(disponibilidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadModel> updateDisponibilidad(
            @PathVariable Integer id,
            @RequestBody DisponibilidadModel disponibilidadDetails) {
        return disponibilidadService.updateDisponibilidad(id, disponibilidadDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisponibilidad(@PathVariable Integer id) {
        Optional<DisponibilidadModel> disponibilidad = disponibilidadService.getDisponibilidadById(id);
        if (disponibilidad.isPresent()) {
            disponibilidadService.deleteDisponibilidad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
