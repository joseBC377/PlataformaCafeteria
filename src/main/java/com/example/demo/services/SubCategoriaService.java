package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.SubCategoria;
import com.example.demo.repositories.SubCategoriaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubCategoriaService {
     private final SubCategoriaRepository repository;

    public List<SubCategoria> selectAll() {
        return repository.findAll();
    }

    public SubCategoria selectId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el id: " + id));
    }

    public SubCategoria insertSubCategoria(SubCategoria subCategoria) {
        return repository.save(subCategoria);
    }

    public SubCategoria updateSubCategoria(Integer id, SubCategoria subCategoria) {
        if (!repository.existsById(subCategoria.getId())) {
            throw new RuntimeException("No se encontró el id " + subCategoria.getId());
        }
        return repository.save(subCategoria);
    }

    public void deleteSubCategoria(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
        System.out.println("Mensaje eliminado " + id);
    }
}
