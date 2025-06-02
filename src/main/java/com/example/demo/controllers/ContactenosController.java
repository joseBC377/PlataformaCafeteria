package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Contactenos;
import com.example.demo.services.ContactenosService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "api/contactenos", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ContactenosController {
    private final ContactenosService service;

    @GetMapping("lista")
    public List<Contactenos> selectAllContactenos() {
        return service.selectAll();
    }

    @GetMapping("lista/{id}")
    public Contactenos selectContactenosById(@PathVariable Integer id) {
        return service.selectId(id);
    }

    @PostMapping("insertar")
    public Contactenos insertContactenos(@RequestBody Contactenos contactenos) {
        return service.insert(contactenos);
    }

    @PutMapping("actualizar/{id}")
    public Contactenos updateContactenos(@PathVariable Integer id, @RequestBody Contactenos contactenos) {
        contactenos.setId(id);
        return service.updateContact(id, contactenos);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> deleteContactenos(@PathVariable Integer id) {
        service.deleteContact(id);
        return ResponseEntity.ok("Mensaje eliminado con Id: " + id);
    }
    //asd
}
