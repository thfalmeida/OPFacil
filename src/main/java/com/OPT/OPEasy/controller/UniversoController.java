package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

import com.OPT.OPEasy.DTO.UniversoDTO;
import com.OPT.OPEasy.Service.UniversoService;
import com.OPT.OPEasy.model.Universo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
@RequestMapping("/universo")
public class UniversoController {
    
    @Autowired
    UniversoService universoService;

    @PostMapping("/cadastrar")
    
    public ResponseEntity<Universo> cadastrarUniverso(@RequestBody @DateTimeFormat(pattern="dd/MM/yyyy") UniversoDTO universo) throws Exception{
        Universo newUniverso = universoService.cadastrarUniverso(universo);
         return new ResponseEntity<Universo>(newUniverso, HttpStatus.OK);
    }


    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Universo> deletarUniverso(@PathVariable Long id) throws Exception {
        Universo deletedUniverso = universoService.deleteUniverso(id);
        return new ResponseEntity<Universo>(deletedUniverso, HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Universo>> listarUniverso(){
        Stream<Universo> universos = universoService.findAll();
        return new ResponseEntity<Stream<Universo>>(universos, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Universo> consultarUniverso(@PathVariable Long id){
        Universo universo = universoService.getUniversoById(id);
        return new ResponseEntity<Universo>(universo, HttpStatus.OK);
    }

    @GetMapping("/ordem/{ordem}")
    public ResponseEntity<Universo> consultarUniverso(@PathVariable String ordem){
        Universo universo = universoService.getUniversoByOrdem(ordem);
        return new ResponseEntity<Universo>(universo, HttpStatus.OK);
    }

    @GetMapping("/transporte/{id}")
    public ResponseEntity<Stream<Universo>> consultarUniversoPorTransporte(@PathVariable Long id) throws Exception{
        Stream<Universo> universos = universoService.findByTransporte(id);
        return new ResponseEntity<Stream<Universo>>(universos, HttpStatus.OK);
    }

}
