package com.Service;

import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Contato;
import com.OPT.OPEasy.repository.ContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    
    @Autowired
    private ContatoRepository contatoRepository;

    public Contato CadastrarContato(Contato contato){
        Contato newContato = new Contato();
        newContato.setAttributes(contato);
        contatoRepository.save(newContato);
        return newContato;
    }

    public Contato updateContato(Contato contato){
        Contato contatoFound = contatoRepository.findById(contato.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Id não encontrado"));
        contatoFound.setAttributes(contato);
        contatoRepository.save(contatoFound);
        return contatoFound;
    }

    public Contato deleteContato(Contato contato){
        contatoRepository.delete(contato);
        return contato;
    }

    public Stream<Contato> findAll(){
        return contatoRepository.findAll().stream();
    }

    public Contato getContatoById(Long id){
        Contato contato = contatoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Id não encontrado"));
        return contato;
    }

}

