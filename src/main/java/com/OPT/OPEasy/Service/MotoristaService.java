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

    public Motorista cadastrarMotorista(Motorista motorista) throws Exception{
        if(hasMotoristaByNick(motorista.getNick()))
            throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
        
        Motorista newMotorista = new Motorista();
        newMotorista.setAttributes(motorista);
        motoristaRepository.save(newMotorista);
        return newMotorista;
    }

    public Motorista updateMotorista(Long id,Motorista motorista) throws Exception{
        Motorista motoristaFound = checkMotoristaUpdate(id, motorista);
        motoristaFound.setAttributes(motorista);
        motoristaRepository.save(motoristaFound);
        return motoristaFound;
    }

    public Motorista deleteMotorista(Motorista motorista){
        Motorista motoristaFound = motoristaRepository.findById(motorista.getId()).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        motoristaRepository.delete(motoristaFound);
        return motoristaFound;
    }

    public Stream<Motorista> findAll(){
        return motoristaRepository.findAll().stream();
    }

    public Motorista getMotoristaByID(Long id){
        Motorista motorista = motoristaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        return motorista;
    }

    public Motorista getMotoristaByNick(String nick){
        Motorista motorista = motoristaRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
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

        //Checa se o esta tentando alterar o nick do motorista,
    //Se estiver, checa se existe outro motorista com o nick, 
    //caso exista, retorna erro, no contrário, o motorista é alterado 
    public Motorista checkMotoristaUpdate(Long id, Motorista motorista) throws Exception{
        Motorista motoristaFound = motoristaRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        if(motorista.getNick() != null){
            String nick = motorista.getNick();

            if(hasMotoristaByNick(nick)){
                Motorista motoristaByNick = motoristaRepository.findByNick(nick).get();
                if(motoristaByNick.getId() == motoristaFound.getId()){
                    return motoristaFound;
                } else{
                    throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
                }
            }
        }
        return motoristaFound;
    }

}
