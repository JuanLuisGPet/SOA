package com.demo.actapartamento.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name = "resenas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResenaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ReservaModel reservacion;
    @Column(name = "calificacion_limpieza")
    private Integer calificacionLimpieza;
    @Column(name = "calificacion_ubicacion")
    private Integer calificacionUbicacion;
    @Column(name = "calificacion_comunicacion")
    private Integer calificacionComunicacion;
    @Column(name = "calificacion_general", nullable = false)
    private Integer calificacionGeneral;
    @Column(columnDefinition = "TEXT")
    private String comentario;
    @Column(name = "respuesta_propietario", columnDefinition = "TEXT")
    private String respuestaPropietario;
    @Column(name = "fecha_resena", nullable = false, updatable = false)
    private LocalDateTime fechaResena;
    @Column(name = "fecha_respuesta")
    private LocalDateTime fechaRespuesta;
    @PrePersist
    protected void onCreate() {
        if (fechaResena == null) {
            fechaResena = LocalDateTime.now();
        }
    }
    @PreUpdate
    protected void onUpdate() {
        if (respuestaPropietario != null && fechaRespuesta == null) {
            fechaRespuesta = LocalDateTime.now();
        }
    }
}
