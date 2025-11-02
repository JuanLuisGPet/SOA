package com.demo.actapartamento.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "disponibilidades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idDisponibilidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad;
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @Column(name = "disponible", nullable = false)
    private Boolean disponible = true;
    @Column(name = "precio_especial", precision = 10, scale = 2)
    private BigDecimal precioEspecial;

    @PrePersist
    public void prePersist() {
        if (this.disponible == null) {
            this.disponible = true;
        }
    }
}
