package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Pedido;
import com.example.demo.repositories.PedidoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PedidoService {

    public PedidoRepository repository;

    public List<Pedido> selectAll(){
        return repository.findAll();
    }
    public Pedido selectId(Integer id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No existe el id: " + id));
    }
    public Pedido insertPedido(Pedido pedido){
        return repository.save(pedido);
    }
    public Pedido updatePedido(Integer id, Pedido pedido){
        if (!repository.existsById(pedido.getId())) {
            throw new RuntimeException("No se encontró el id "+pedido.getId());
        }
        return repository.save(pedido);
    }
    public void deletePedido(Integer id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id: " + id);
        }
        repository.deleteById(id);
    }
}
