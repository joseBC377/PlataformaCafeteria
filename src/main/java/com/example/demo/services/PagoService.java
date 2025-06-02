package com.example.demo.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.demo.entities.Pago;
import com.example.demo.repositories.PagoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PagoService {

    private PagoRepository repository;

    public List<Pago> selectAll(){
        return repository.findAll();
    }
    public Pago sellectById(Integer id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("No existe el id: "+id));
    }
    public Pago insertPago(Pago pago){
        return repository.save(pago);
    }
    public Pago updatePago(Integer id, Pago pago){
        if (!repository.existsById(pago.getId())) {
            throw new RuntimeException("No se encontro el id:"+id);
        }
        return repository.save(pago);
    }
    public void deletePago(Integer id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encuentra el id:"+id);
        }
        repository.deleteById(id);
    }

}
