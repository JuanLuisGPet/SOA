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
import com.demo.actapartamento.enums.EstadoPago;
import com.demo.actapartamento.enums.MetodoPago;
import com.demo.actapartamento.models.PagosModel;
import com.demo.actapartamento.services.PagosService;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {
    @Autowired
    private PagosService pagosService;

    @GetMapping
    public List<PagosModel> getAllPagos() {
        return pagosService.getAllPagos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosModel> getPagoById(@PathVariable Long id) {
        Optional<PagosModel> pago = pagosService.getPagoById(id);
        return pago.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/reserva/{id}")
    public List<PagosModel> getPagosByReserva(@PathVariable Long id) {
        return pagosService.getPagosByReserva(id);
    }

    @GetMapping("/estado/{estado}")
    public List<PagosModel> getPagosByEstado(@PathVariable EstadoPago estado) {
        return pagosService.getPagosByEstado(estado);
    }

    @GetMapping("/metodo/{metodo}")
    public List<PagosModel> getPagosByMetodoPago(@PathVariable MetodoPago metodo) {
        return pagosService.getPagosByMetodoPago(metodo);
    }

    @GetMapping("/reserva/{id}/estado")
    public List<PagosModel> getPagosByReservaAndEstado(
            @PathVariable Long id,
            @RequestParam EstadoPago estado) {
        return pagosService.getPagosByReservaAndEstado(id, estado);
    }

    @PostMapping
    public PagosModel createPago(@RequestBody PagosModel pago) {
        return pagosService.savePago(pago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagosModel> updatePago(
            @PathVariable Long id,
            @RequestBody PagosModel pagoDetails) {
        return pagosService.updatePago(id, pagoDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        Optional<PagosModel> pago = pagosService.getPagoById(id);
        if (pago.isPresent()) {
            pagosService.deletePago(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
