package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {

}
