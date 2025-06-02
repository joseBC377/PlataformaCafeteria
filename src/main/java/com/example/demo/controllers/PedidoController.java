package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pedido;
import com.example.demo.services.PedidoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/pedido", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PedidoController {
    public PedidoService service;

    @GetMapping("lista/")
    public List<Pedido> selectAll() {
        return service.selectAll();
    }

    @GetMapping("lista/{id}")
    public Pedido selectPedidoId(@PathVariable Integer id) {
        return service.selectId(id);
    }

    @PostMapping("insertar")
    public Pedido insertPedido(@RequestBody Pedido pedido) {
        return service.insertPedido(pedido);
    }

    @PutMapping("actualizar/{id}")
    public Pedido updatePedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        pedido.setId(id);
        return service.updatePedido(id, pedido);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Integer id) {
        service.deletePedido(id);
        return ResponseEntity.ok("Productos eliminada correctamente. ID: " + id);
    }
}
