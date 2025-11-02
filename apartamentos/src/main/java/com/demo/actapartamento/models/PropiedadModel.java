package com.demo.actapartamento.models;
import java.time.LocalDateTime;
import com.demo.actapartamento.enums.EstadoPropiedad;
import com.demo.actapartamento.enums.TipoPropiedad;
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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "propiedades")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropiedadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idPropiedad;
    @NotNull(message = "El propietario es requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private ClienteModel propietario;
    @NotNull(message = "El tipo de propiedad es requerido")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_propiedad", nullable = false)
    private TipoPropiedad tipo;
    @NotBlank(message = "El título es requerido")
    @Size(min = 5, max = 100, message = "El título debe tener entre 5 y 100 caracteres")
    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;
    @NotBlank(message = "La descripción es requerida")
    @Size(min = 20, max = 500, message = "La descripción debe tener entre 20 y 500 caracteres")
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @NotBlank(message = "La dirección es requerida")
    @Size(max = 150, message = "La dirección no puede exceder 150 caracteres")
    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;
    @NotBlank(message = "La ciudad es requerida")
    @Size(max = 50, message = "La ciudad no puede exceder 50 caracteres")
    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;
    @Min(value = 10000, message = "El código postal debe ser de 5 dígitos")
    @Column(name = "codigo_postal", nullable = false, length = 5)
        private int codigoPostal;
    @NotBlank(message = "El país es requerido")
    @Size(max = 50, message = "El país no puede exceder 50 caracteres")
    @Column(name = "pais", nullable = false, length = 50)
    private String pais;
    @DecimalMin(value = "-90.0", message = "La latitud debe estar entre -90 y 90")
    @DecimalMax(value = "90.0", message = "La latitud debe estar entre -90 y 90")
    @Column(name = "latitud", nullable = false)
    private double latitud;
    @DecimalMin(value = "-180.0", message = "La longitud debe estar entre -180 y 180")
    @DecimalMax(value = "180.0", message = "La longitud debe estar entre -180 y 180")
    @Column(name = "longitud", nullable = false)
    private double longitud;
    @Positive(message = "El precio por noche debe ser mayor a 0")
    @Column(name = "precio_noche", nullable = false)
        private double precioNoche;
    @Min(value = 1, message = "La capacidad debe ser al menos 1 persona")
    @Column(name = "capacidad", nullable = false)
    private int capacidad;
    @Min(value = 1, message = "Debe tener al menos 1 habitación")
    @Column(name = "num_habitaciones", nullable = false)
        private int numHabitaciones;
    @Min(value = 1, message = "Debe tener al menos 1 baño")
    @Column(name = "num_banos", nullable = false)
        private int numBanos;
    @Min(value = 10, message = "Los metros cuadrados deben ser al menos 10")
    @Column(name = "metro_cuadrados", nullable = false)
        private int metroCuadrados;
    @Size(max = 300, message = "Las comodidades no pueden exceder 300 caracteres")
    @Column(name = "comodidades", length = 300)
    private String comodidades;
    @Size(max = 300, message = "Las reglas no pueden exceder 300 caracteres")
    @Column(name = "reglas", length = 300)
    private String reglas;
    @NotNull(message = "El estado de la propiedad es requerido")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_propiedad", nullable = false)
    private EstadoPropiedad estadoPropiedad;
    @Column(name = "fecha_registro", nullable = false)
        private LocalDateTime fechaRegistro;
    @Column(name = "fecha_actualizacion", nullable = false)
        private LocalDateTime fechaActualizacion;
        @PrePersist
    public void onPrePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        this.fechaActualizacion = LocalDateTime.now();
    }
    @PreUpdate
    public void onPreUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
