package com.demo.actapartamento.controllers;

import java.util.List;
import java.util.Map;
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
import com.demo.actapartamento.models.ResenaModel;
import com.demo.actapartamento.services.ResenaService;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public List<ResenaModel> getAllResenas() {
        return resenaService.getAllResenas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaModel> getResenaById(@PathVariable Integer id) {
        Optional<ResenaModel> resena = resenaService.getResenaById(id);
        return resena.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/reserva/{id}")
    public List<ResenaModel> getResenasByReservacion(@PathVariable Long id) {
        return resenaService.getResenasByReservacion(id);
    }

    @GetMapping("/propiedad/{id}")
    public List<ResenaModel> getResenasByPropiedad(@PathVariable Long id) {
        return resenaService.getResenasByPropiedad(id);
    }

    @GetMapping("/cliente/{id}")
    public List<ResenaModel> getResenasByCliente(@PathVariable Long id) {
        return resenaService.getResenasByCliente(id);
    }

    @GetMapping("/con-respuesta")
    public List<ResenaModel> getResenasWithRespuesta() {
        return resenaService.getResenasWithRespuesta();
    }

    @PostMapping
    public ResenaModel createResena(@RequestBody ResenaModel resena) {
        return resenaService.saveResena(resena);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenaModel> updateResena(
            @PathVariable Integer id,
            @RequestBody ResenaModel resenaDetails) {
        return resenaService.updateResena(id, resenaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/respuesta")
    public ResponseEntity<ResenaModel> agregarRespuestaPropietario(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        String respuesta = body.get("respuesta");
        if (respuesta == null || respuesta.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return resenaService.agregarRespuestaPropietario(id, respuesta)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable Integer id) {
        Optional<ResenaModel> resena = resenaService.getResenaById(id);
        if (resena.isPresent()) {
            resenaService.deleteResena(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
