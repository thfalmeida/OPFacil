package com.OPT.OPEasy.Service;

import java.util.Optional;
import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Mercado;
import com.OPT.OPEasy.repository.MercadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MercadoService {

    @Autowired
    private MercadoRepository mercadoRepository;


    public Mercado cadastrarMercado(Mercado mercado){
        if(hasMercadoByNick(mercado.getNick()))
            throw new ResourceNotFoundException("Nick já usado");

        Mercado newMercado = new Mercado();
        newMercado.setAttributes(mercado);
        mercadoRepository.save(newMercado);
        return newMercado;
    }

    public Mercado updateMercado(Long id,Mercado mercado){
        System.out.println("Testando id:" + id);
        Mercado mercadoFound = mercadoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Mercado não encontrado"));
        
        mercadoFound.setAttributes(mercado);
        mercadoRepository.save(mercadoFound);
        return mercadoFound;
    }

    public Mercado deleteMercado(Mercado mercado){
        Mercado mercadoFound = mercadoRepository.findById(mercado.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Motorista não encontrado"));

            mercadoRepository.delete(mercadoFound);
        return mercadoFound;
    }

    public Stream<Mercado> findAll(){
        return mercadoRepository.findAll().stream();
    }

    public Mercado getMercadoByID(Long id){
        Mercado mercado = mercadoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Mercado não encontrado"));
        return mercado;
    }

    public Mercado getMercadoByNick(String nick){
        Mercado mercado = mercadoRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("Mercado não encontrado"));
        return mercado;
    }

    public boolean hasMercadoById(Long id){
        Optional<Mercado> mercado = mercadoRepository.findById(id);
        return mercado.isPresent();
    }

    public boolean hasMercadoByNick(String nick){
        Optional<Mercado> mercado = mercadoRepository.findByNick(nick);
        return mercado.isPresent();
    }
    
}
