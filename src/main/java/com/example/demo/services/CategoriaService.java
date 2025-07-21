package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Categoria;
import com.example.demo.repositories.CategoriaRepository;
import org.springframework.data.domain.Sort;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {
    private final CategoriaRepository repository;

    public List<Categoria> selectAll() {
        return repository.findAll(Sort.by("id").ascending());
    }

    public Categoria selectId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el id: " + id));
    }

    public Categoria insert(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria updateCategoria(Integer id, Categoria categoria) {
        if (!repository.existsById(categoria.getId())) {
            throw new RuntimeException("No se encontró el id " + categoria.getId());
        }
        return repository.save(categoria);
    }

    public void deleteCategoria(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
        System.out.println("Mensaje eliminado " + id);
    }
}
