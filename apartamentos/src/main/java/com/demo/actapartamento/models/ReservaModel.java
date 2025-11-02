package com.demo.actapartamento.models;
import java.time.LocalDateTime;
import com.demo.actapartamento.enums.EstadoReserva;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idReserva;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ClienteModel cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private PropiedadModel propiedad;
    @Column(name = "fecha_inicio", nullable = false)
        private LocalDateTime fechaInicio;
    @Column(name = "fecha_fin", nullable = false)
        private LocalDateTime fechaFin;
    @Column(name = "num_huespedes", nullable = false)
        private Integer numHuespedes;
    @Column(name = "precio_total", nullable = false)
        private Double precioTotal;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false)
    private EstadoReserva estado;
    @Column(name = "observaciones", length = 500)
    private String observaciones;
    @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
        private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
                if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
                this.updatedAt = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoReserva.PENDIENTE;
        }
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
