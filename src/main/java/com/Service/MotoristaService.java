package com.Service;

import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Motorista;
import com.OPT.OPEasy.repository.MotoristaRepository;

import org.springframework.stereotype.Service;

@Service
public class MotoristaService {
    
    private MotoristaRepository motoristaRepository;

    public Motorista cadastrarMotorista(Motorista motorista){
        Motorista newMotorista = new Motorista();
        newMotorista.setAttributes(motorista);
        motoristaRepository.save(newMotorista);
        return newMotorista;
    }

    public Motorista updateMotorista(Motorista motorista){
        Motorista motoristaFounded = motoristaRepository.findById(motorista.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));
        motoristaFounded.setAttributes(motorista);
        motoristaRepository.save(motoristaFounded);
        return motoristaFounded;
    }

    public Motorista deleteMotorista(Motorista motorista){
        Motorista motoristaFounded = motoristaRepository.findById(motorista.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));

        motoristaRepository.delete(motoristaFounded);
        return motoristaFounded;
    }

    public Stream<Motorista> findAll(){
        return motoristaRepository.findAll().stream();
    }

    public Motorista getMotoristaById(Long id){
        Motorista motorista = motoristaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));
        return motorista;
    }
}
