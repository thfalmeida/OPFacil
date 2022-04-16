package com.OPT.OPEasy.Service;

import java.util.stream.Stream;

import com.OPT.OPEasy.Util.ResourceNotFoundException;
import com.OPT.OPEasy.model.Contato;
import com.OPT.OPEasy.repository.ContatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {
    
    @Autowired
    ContatoRepository contatoRepository;

    public Contato CadastrarContato(Contato contato){
        System.out.println("Cadastrando o contato :\n " + contato.toString());
        Contato newContato = new Contato();
        newContato.setAttributes(contato);
        contatoRepository.save(newContato);
        return newContato;
    }

    public Contato updateContato(Long id,Contato contato){
        Contato contatoFound = contatoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Id não encontrado"));
        contatoFound.setAttributes(contato);
        contatoRepository.save(contatoFound);
        return contatoFound;
    }

    public Contato deleteContato(Contato contato){
        contatoRepository.findById(contato.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Id não encontrado"));
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

    public Contato getContatoByNick(String nick){
        Contato contato = contatoRepository.findByNick(nick).get();
        return contato;
    }

}

