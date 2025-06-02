package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Contactenos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contactenos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Ingrese apellido")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener números")
    @Size(min = 2, max = 100, message = "Mínimo 2 y máximo 100 caracteres")
    private String apellido;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Ingrese nombre")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "No puede contener números")
    @Size(min = 2, max = 100, message = "Mínimo 2 y máximo 100 caracteres")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Email(message = "Formato de correo inválido")
    @NotBlank(message = "Ingrese correo")
    private String correo;

    @Lob
    @Column(nullable = false)
    @NotBlank(message = "Ingrese mensaje")
    private String mensaje;

    // Puede estar vacío (nullable = true)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    private Usuario id_usuario;
}
