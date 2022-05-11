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


    public Mercado cadastrarMercado(Mercado mercado) throws Exception{
        if(hasMercadoByNick(mercado.getNick()))
            throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");

        Mercado newMercado = new Mercado();
        newMercado.setAttributes(mercado);
        mercadoRepository.save(newMercado);
        return newMercado;
    }

    public Mercado updateMercado(Long id,Mercado mercado) throws Exception{
        Mercado mercadoFound = checkMercadoUpdate(id, mercado);
        
        mercadoFound.setAttributes(mercado);
        mercadoRepository.save(mercadoFound);
        return mercadoFound;
    }

    public Mercado deleteMercado(Mercado mercado){
        Mercado mercadoFound = mercadoRepository.findById(mercado.getId()).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

            mercadoRepository.delete(mercadoFound);
        return mercadoFound;
    }

    public Stream<Mercado> findAll(){
        return mercadoRepository.findAll().stream();
    }

    public Mercado getMercadoByID(Long id){
        Mercado mercado = mercadoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
        return mercado;
    }

    public Mercado getMercadoByNick(String nick){
        Mercado mercado = mercadoRepository.findByNick(nick).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));
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

        //Checa se o esta tentando alterar o nick do mercado,
    //Se estiver, checa se existe outro mercado com o nick, 
    //caso exista, retorna erro, no contrário, o mercado é alterado 
    public Mercado checkMercadoUpdate(Long id, Mercado mercado) throws Exception{
        Mercado mercadoFound = mercadoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("O ID informado não foi encontrado."));

        if(mercado.getNick() != null){
            String nick = mercado.getNick();

            if(hasMercadoByNick(nick)){
                Mercado mercadoByNick = mercadoRepository.findByNick(nick).get();
                if(mercadoByNick.getId() == mercadoFound.getId()){
                    return mercadoFound;
                } else{
                    throw new Exception("Nick informado já se encontra cadastrado. Tente outro.");
                }
            }
        }
        return mercadoFound;
    }
    
}
