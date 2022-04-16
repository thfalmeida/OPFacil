package com.OPT.OPEasy.Service;

import java.util.Optional;
import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Motorista;
import com.OPT.OPEasy.repository.MotoristaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotoristaService {
    
    @Autowired
    private MotoristaRepository motoristaRepository;

    public Motorista cadastrarMotorista(Motorista motorista){
        Optional<Motorista> motoristaFound = motoristaRepository.findByNick(motorista.getNick());
        if(motoristaFound.isPresent())
            throw new ResourceNotFoundException("Nick já usado");
        Motorista newMotorista = new Motorista();
        newMotorista.setAttributes(motorista);
        motoristaRepository.save(newMotorista);
        return newMotorista;
    }

    public Motorista updateMotorista(Long id,Motorista motorista){
        Motorista motoristaFounded = motoristaRepository.findById(id).orElseThrow(
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

    public Motorista getMotoristaByID(Long id){
        Motorista motorista = motoristaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));
        return motorista;
    }

    public Motorista getMotoristaByNick(String nick){
        Motorista motorista = motoristaRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));
        return motorista;
    }

    public boolean hasMotoristaById(Long id){
        Optional<Motorista> motorista = motoristaRepository.findById(id);
        return motorista.isPresent();
    }
    public boolean hasMotoristaByNick(String nick){
        Optional<Motorista> motorista = motoristaRepository.findByNick(nick);
        return motorista.isPresent();
    }

}
