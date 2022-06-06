package com.OPT.OPEasy.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

import com.OPT.OPEasy.DTO.ViagemDTO;
import com.OPT.OPEasy.DTO.ViagemRelatorioDTO;
import com.OPT.OPEasy.Service.ViagemService;
import com.OPT.OPEasy.model.Viagem;

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
@RequestMapping("/viagem")
@Controller
public class ViagemController {
    @Autowired
    ViagemService viagemService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Viagem> cadastrarViagem(@RequestBody ViagemDTO viagem){
        Viagem newViagem = viagemService.cadastrarViagem(viagem);
        return new ResponseEntity<Viagem>(newViagem, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Viagem> atualizarViagem(@PathVariable Long id, @RequestBody ViagemDTO viagem){
        Viagem newViagem = viagemService.updateViagem(id, viagem);
        return new ResponseEntity<Viagem>(newViagem, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Viagem> deleteViagem(@PathVariable Long id){
        Viagem viagem = viagemService.getViagemById(id);
        viagemService.deleteViagem(viagem);
        return new ResponseEntity<Viagem>(viagem, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Viagem>> listarViagems(){
        Stream<Viagem> viagems = viagemService.findAll();
        return new ResponseEntity<>(viagems, HttpStatus.OK);
    }

    @GetMapping("/motorista/{id}")
    public ResponseEntity<Stream<Viagem>> consultarViagemByMotorista(@PathVariable Long id){
        Stream<Viagem> viagens = viagemService.getViagemByMotorista(id);
        return new ResponseEntity<Stream<Viagem>>(viagens, HttpStatus.OK);
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<Stream<Viagem>> consultarViagemByEmpresa(@PathVariable Long id){
        Stream<Viagem> viagens = viagemService.getViagemByEmpresa(id);
        return new ResponseEntity<Stream<Viagem>>(viagens, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Viagem> consultarViagem(@PathVariable Long id){
        Viagem viagem = viagemService.getViagemById(id);
        return new ResponseEntity<Viagem>(viagem, HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<Stream<Viagem>> gerarRelatorio(@RequestBody ViagemRelatorioDTO rel) throws FileNotFoundException, IOException{
        Stream<Viagem>viagens = viagemService.gerarRelatorio(rel);
        return new ResponseEntity<>(viagens, HttpStatus.OK);
    }
}
