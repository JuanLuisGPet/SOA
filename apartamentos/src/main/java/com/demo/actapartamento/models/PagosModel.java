package com.demo.actapartamento.models;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.demo.actapartamento.enums.EstadoPago;
import com.demo.actapartamento.enums.MetodoPago;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPago;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ReservaModel reserva;
    @Column(name = "monto", nullable = false)
    private Double monto;
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
        private MetodoPago metodoPago;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false)
        private EstadoPago estadoPago;
    @Column(name = "fecha_pago", nullable = false)
        private LocalDate fechaPago;
    @Column(name = "referencia_pago", nullable = false, length = 100)
    private String referenciaPago;
    @Column(name = "detalles_pago", length = 500)
        private String detallesPago;
    @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;
        @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
        if (this.fechaPago == null) {
            this.fechaPago = LocalDate.now();
        }
        if (this.estadoPago == null) {
            this.estadoPago = EstadoPago.PENDIENTE;
        }
    }
}
