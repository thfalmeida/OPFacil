package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

import com.OPT.OPEasy.Service.MotoristaService;
import com.OPT.OPEasy.model.Motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/motorista")
public class MotoristaController {
    
    @Autowired
    MotoristaService motoristaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Motorista> cadastrarMotorista(@RequestBody Motorista motorista){
        Motorista newMotorista = motoristaService.cadastrarMotorista(motorista);
        return new ResponseEntity<Motorista>(newMotorista, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Motorista> atualizarMotorista(@PathVariable Long id, @RequestBody Motorista motorista){
        Motorista newMotorista = motoristaService.updateMotorista(id, motorista);
        return new ResponseEntity<Motorista>(newMotorista, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Motorista> deleteMotorista(@PathVariable Long id){
        Motorista motorista = motoristaService.getMotoristaByID(id);
        motoristaService.deleteMotorista(motorista);
        return new ResponseEntity<Motorista>(motorista, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Motorista>> listarMotoristas(){
        Stream<Motorista> motoristas = motoristaService.findAll();
        return new ResponseEntity<>(motoristas, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Motorista> consultarMotorista(@PathVariable Long id){
        Motorista motorista = motoristaService.getMotoristaByID(id);
        return new ResponseEntity<Motorista>(motorista, HttpStatus.OK);
    }

    @GetMapping("/nick/{nick}")
    public ResponseEntity<Motorista> consultarMotorista(@PathVariable String nick){
        Motorista motorista = motoristaService.getMotoristaByNick(nick);
        return new ResponseEntity<Motorista>(motorista, HttpStatus.OK);
    }
}
