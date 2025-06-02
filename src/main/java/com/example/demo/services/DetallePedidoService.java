package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.DetallePedido;
import com.example.demo.repositories.DetallePedidoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DetallePedidoService {

    public DetallePedidoRepository repository;

     public List<DetallePedido> selectAll(){
        return repository.findAll();
    }
    public DetallePedido selectId(Integer id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No existe el id: " + id));
    }
    public DetallePedido insertDetallePedido(DetallePedido detallePedido){
        return repository.save(detallePedido);
    }
    public DetallePedido updateDetallePedido(Integer id, DetallePedido detallePedido){
        if (!repository.existsById(detallePedido.getId())) {
            throw new RuntimeException("No se encontró el id "+detallePedido.getId());
        }
        return repository.save(detallePedido);
    }
    public void deleteDetallePedido(Integer id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
    }
}
