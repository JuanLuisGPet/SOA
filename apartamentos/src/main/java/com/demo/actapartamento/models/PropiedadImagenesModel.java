package com.demo.actapartamento.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name = "propiedad_imagenes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropiedadImagenesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_imagen;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PropiedadModel propiedad;
    @Column(name = "url_imagen", nullable = false, length = 500)
    private String urlImagen;
    @Column(nullable = false)
    private Integer orden;
    @Column(name = "es_principal", nullable = false)
    private Boolean esPrincipal = false;
    @Column(name = "fecha_subida", nullable = false, updatable = false)
    private LocalDateTime fechaSubida;
    @PrePersist
    protected void onCreate() {
        if (fechaSubida == null) {
            fechaSubida = LocalDateTime.now();
        }
        if (esPrincipal == null) {
            esPrincipal = false;
        }
        if (orden == null) {
            orden = 0;
        }
    }
}
