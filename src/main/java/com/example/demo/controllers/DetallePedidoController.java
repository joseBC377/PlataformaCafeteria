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

import com.example.demo.entities.DetallePedido;
import com.example.demo.services.DetallePedidoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/detallepedido", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class DetallePedidoController {
    public DetallePedidoService service;

    @GetMapping("lista/")
    public List<DetallePedido> selectAll() {
        return service.selectAll();
    }

    @GetMapping("lista/{id}")
    public DetallePedido selectDetallePedidoId(@PathVariable Integer id) {
        return service.selectId(id);
    }

    @PostMapping("insertar")
    public DetallePedido insertDetallePedido(@RequestBody DetallePedido detallepedido) {
        return service.insertDetallePedido(detallepedido);
    }

    @PutMapping("actualizar/{id}")
    public DetallePedido updateDetallePedido(@PathVariable Integer id, @RequestBody DetallePedido detallepedido) {
        detallepedido.setId(id);
        return service.updateDetallePedido(id, detallepedido);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteDetallePedido(@PathVariable Integer id) {
        service.deleteDetallePedido(id);
        return ResponseEntity.ok("Productos eliminada correctamente. ID: " + id);
    }
}
