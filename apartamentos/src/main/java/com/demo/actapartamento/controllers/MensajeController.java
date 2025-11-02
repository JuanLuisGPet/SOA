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
import com.demo.actapartamento.models.MensajeModel;
import com.demo.actapartamento.services.MensajeService;
@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;
    @GetMapping
    public List<MensajeModel> getAllMensajes() {
        return mensajeService.getAllMensajes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MensajeModel> getMensajeById(@PathVariable Integer id) {
        Optional<MensajeModel> mensaje = mensajeService.getMensajeById(id);
        return mensaje.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/enviados/{id}")
    public List<MensajeModel> getMensajesEnviados(@PathVariable Long id) {
        return mensajeService.getMensajesEnviados(id);
    }
    @GetMapping("/recibidos/{id}")
    public List<MensajeModel> getMensajesRecibidos(@PathVariable Long id) {
        return mensajeService.getMensajesRecibidos(id);
    }
    @GetMapping("/no-leidos/{id}")
    public List<MensajeModel> getMensajesNoLeidos(@PathVariable Long id) {
        return mensajeService.getMensajesNoLeidos(id);
    }
    @GetMapping("/chat/{id1}/{id2}")
    public List<MensajeModel> getConversacion(
            @PathVariable Long id1,
            @PathVariable Long id2) {
        return mensajeService.getConversacion(id1, id2);
    }
    @GetMapping("/reserva/{id}")
    public List<MensajeModel> getMensajesByReservacion(@PathVariable Integer id) {
        return mensajeService.getMensajesByReservacion(Long.valueOf(id));
    }
    @PostMapping
    public MensajeModel createMensaje(@RequestBody MensajeModel mensaje) {
        return mensajeService.saveMensaje(mensaje);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MensajeModel> updateMensaje(
            @PathVariable Integer id,
            @RequestBody MensajeModel mensajeDetails) {
        return mensajeService.updateMensaje(id, mensajeDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}/leer")
    public ResponseEntity<MensajeModel> marcarComoLeido(@PathVariable Integer id) {
        return mensajeService.marcarComoLeido(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/leer-todos/{id}")
    public ResponseEntity<Void> marcarTodosComoLeidos(@PathVariable Long id) {
        mensajeService.marcarTodosComoLeidos(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensaje(@PathVariable Integer id) {
        Optional<MensajeModel> mensaje = mensajeService.getMensajeById(id);
        if (mensaje.isPresent()) {
            mensajeService.deleteMensaje(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
