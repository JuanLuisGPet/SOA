package com.demo.actapartamento.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name = "mensajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_remitente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ClienteModel remitente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinatario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ClienteModel destinatario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservaModel reservacion;
    @Column(nullable = false, length = 255)
    private String asunto;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;
    @Column(nullable = false)
    private Boolean leido = false;
    @Column(name = "fecha_envio", nullable = false, updatable = false)
    private LocalDateTime fechaEnvio;
    @PrePersist
    protected void onCreate() {
        if (fechaEnvio == null) {
            fechaEnvio = LocalDateTime.now();
        }
        if (leido == null) {
            leido = false;
        }
    }
}
