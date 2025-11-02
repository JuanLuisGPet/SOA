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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.actapartamento.enums.EstadoReserva;
import com.demo.actapartamento.models.ReservaModel;
import com.demo.actapartamento.services.ReservaService;
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;
    @GetMapping
    public List<ReservaModel> getAllReservas() {
        return reservaService.getAllReservas();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaModel> getReservaById(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        return reserva.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/cliente/{id}")
    public List<ReservaModel> getReservasByCliente(@PathVariable Long id) {
        return reservaService.getReservasByCliente(id);
    }
    @GetMapping("/propiedad/{id}")
    public List<ReservaModel> getReservasByPropiedad(@PathVariable Long id) {
        return reservaService.getReservasByPropiedad(id);
    }
    @GetMapping("/estado/{estado}")
    public List<ReservaModel> getReservasByEstado(@PathVariable EstadoReserva estado) {
        return reservaService.getReservasByEstado(estado);
    }
    @GetMapping("/cliente/{id}/estado")
    public List<ReservaModel> getReservasByClienteAndEstado(
            @PathVariable Long id,
            @RequestParam EstadoReserva estado) {
        return reservaService.getReservasByClienteAndEstado(id, estado);
    }
    @PostMapping
    public ReservaModel createReserva(@RequestBody ReservaModel reserva) {
        return reservaService.saveReserva(reserva);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservaModel> updateReserva(
            @PathVariable Long id,
            @RequestBody ReservaModel reservaDetails) {
        return reservaService.updateReserva(id, reservaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        Optional<ReservaModel> reserva = reservaService.getReservaById(id);
        if (reserva.isPresent()) {
            reservaService.deleteReserva(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
