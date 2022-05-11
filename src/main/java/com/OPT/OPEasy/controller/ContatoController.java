package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

import com.OPT.OPEasy.Service.ContatoService;
import com.OPT.OPEasy.model.Contato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    ContatoService contatoService;


    @PostMapping("/cadastrar")
    public ResponseEntity<Contato> cadastrarContato(@RequestBody Contato contato) throws Exception{
        Contato newContato = contatoService.CadastrarContato(contato);
        return new ResponseEntity<Contato>(newContato, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contato) throws Exception{
        Contato newContato = contatoService.updateContato(id, contato);
        return new ResponseEntity<Contato>(newContato, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Contato> deleteContato(@PathVariable Long id){
        Contato contato = contatoService.getContatoById(id);
        contatoService.deleteContato(contato);
        return new ResponseEntity<Contato>(contato, HttpStatus.OK);
    }

    
    @GetMapping("/list")
    public ResponseEntity<Stream<Contato>> listarContatos(){
        Stream<Contato> contatos = contatoService.findAll();
        return new ResponseEntity<>(contatos, HttpStatus.OK);
    }

    
    @GetMapping("/id/{id}")
    public ResponseEntity<Contato> consultarContato(@PathVariable Long id){
        Contato contato = contatoService.getContatoById(id);
        return new ResponseEntity<Contato>(contato, HttpStatus.OK);
    }

    @GetMapping("/nick/{nick}")
    public ResponseEntity<Contato> consultarContato(@PathVariable String nick){
        Contato contato = contatoService.getContatoByNick(nick);
        return new ResponseEntity<Contato>(contato, HttpStatus.OK);
    }

}
