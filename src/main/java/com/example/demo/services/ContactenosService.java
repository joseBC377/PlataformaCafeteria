package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Contactenos;
import com.example.demo.repositories.ContactoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactenosService {

    private final ContactoRepository repository;

    public List<Contactenos> selectAll() {
        return repository.findAll();
    }

    public Contactenos selectId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el id: " + id));
    }

    public Contactenos insert(Contactenos contactenos) {
        return repository.save(contactenos);
    }

    public Contactenos updateContact(Integer id, Contactenos contactenos) {
        if (!repository.existsById(contactenos.getId())) {
            throw new RuntimeException("No se encontró el id " + contactenos.getId());
        }
        return repository.save(contactenos);
    }

    public void deleteContact(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
        System.out.println("Mensaje eliminado " + id);
    }
}
