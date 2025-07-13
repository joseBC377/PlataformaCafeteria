package com.example.demo.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
// import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pago")
public class Pago {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(nullable = false)
@NotNull(message = "El total no puede estar vacio")
private BigDecimal total;

@Column(length = 30, nullable = false)
@NotBlank(message = "El metodo de pago es obligatorio")
@Size(max = 30, message = "El metodo de pago no puede tener mas de 30 caracteres")
private String metodo_pago;

@Column(nullable = false)
@NotNull(message = "La fecha de pago es obligatoria")
private LocalDate fecha_pago;

@ManyToOne
@JoinColumn(name = "id_pedido",nullable = false)
@NotNull(message= "El id de pedido es obligatorio")
private Pedido pedido;
}
