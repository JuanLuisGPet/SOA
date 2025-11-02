package com.demo.actapartamento.models;
import java.time.LocalDateTime;
import java.util.Date;
import com.demo.actapartamento.enums.ClienteStatus;
import com.demo.actapartamento.enums.TipoCliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    @NotNull(message = "El tipo de cliente es requerido")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false, length = 20)
    private TipoCliente tipo = TipoCliente.CLIENTE;
    @NotBlank(message = "El nombre es requerido")
    @Size(min = 2, max = 35, message = "El nombre debe tener entre 2 y 35 caracteres")
    @Column(name = "nombre", nullable = false, length = 35)
    private String nombres;
    @NotBlank(message = "Los apellidos son requeridos")
    @Size(min = 2, max = 35, message = "Los apellidos deben tener entre 2 y 35 caracteres")
    @Column(name = "apellidos", nullable = false, length = 35)
    private String apellidos;
    @NotBlank(message = "El email es requerido")
    @Email(message = "El email debe ser válido")
    @Size(max = 50, message = "El email no puede exceder 50 caracteres")
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener 10 dígitos")
    @Column(name = "telefono", length = 15)
    private String telefono;
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    @Size(min = 18, max = 20, message = "El INE debe tener entre 18 y 20 caracteres")
    @Column(name = "numero_ine", unique = true, length = 20)
    private String ine;
    @Size(max = 100, message = "La dirección no puede exceder 100 caracteres")
    @Column(name = "direccion", length = 100)
    private String direccion;
    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;
    @NotNull(message = "El estatus es requerido")
    @Enumerated(EnumType.STRING)
    @Column(name = "estatus", nullable = false)
    private ClienteStatus estatus;
    @NotBlank(message = "La contraseña es requerida")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @PrePersist
    public void prePersist() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        if (this.estatus == null) {
            this.estatus = ClienteStatus.ACTIVO;
        }
        if (this.tipo == null) {
            this.tipo = TipoCliente.CLIENTE;
        }
    }
}
