package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Pago;
import com.example.demo.services.PagoService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "api/pago", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PagoController {
    public PagoService service;

    @GetMapping("lista/")
    public List<Pago> selectAll() {
        return service.selectAll();
    }

    @GetMapping("lista/{id}")
    public Pago selectPagoId(@PathVariable Integer id) {
        return service.sellectById(id);
    }

    @PostMapping("insertar")
    public Pago insertPago(@RequestBody Pago pago) {
        return service.insertPago(pago);
    }

    @PutMapping("actualizar/{id}")
    public Pago updatePago(@PathVariable Integer id, @RequestBody Pago pago) {
        pago.setId(id);
        return service.updatePago(id, pago);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deletePago(@PathVariable Integer id) {
        service.deletePago(id);
        return ResponseEntity.ok("Productos eliminada correctamente. ID: " + id);
    }

}
