package com.demo.actapartamento.controllers;
import com.demo.actapartamento.models.ClienteModel;
import com.demo.actapartamento.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping
    public List<ClienteModel> getAllClientes() {
        return clienteService.getAllClientes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long id) {
        Optional <ClienteModel> cliente = clienteService.getClienteById(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ClienteModel createCliente(@Valid @RequestBody ClienteModel cliente) {
        return clienteService.saveCliente(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @Valid @RequestBody ClienteModel clienteDetails) {
        return clienteService.updateCliente(id, clienteDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        Optional<ClienteModel> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            clienteService.deleteCliente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
