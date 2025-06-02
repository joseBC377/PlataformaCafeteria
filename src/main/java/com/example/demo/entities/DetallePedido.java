package com.example.demo.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Detalle_Pedido")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_pedido",nullable = false)
    @NotNull(message = "El id_pedido es obligatorio")
    private Pedido pedido;

    // private Producto Producto;

    @Column(nullable = false)
    @Min(value =  1, message="La cantidad debe ser al menos 1")
    private int cantidad;
    
    @NotNull(message = "El precio unitario es obligatorio")
    @Column(nullable = false)
    private BigDecimal precio_unitario;
}
