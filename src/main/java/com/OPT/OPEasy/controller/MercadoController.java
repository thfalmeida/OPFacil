package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

import com.OPT.OPEasy.Service.MercadoService;
import com.OPT.OPEasy.model.Mercado;

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
@RequestMapping("/mercado")
public class MercadoController {

    @Autowired
    private MercadoService mercadoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Mercado> cadastrarMercado(@RequestBody Mercado mercado){
        Mercado newMercado = mercadoService.cadastrarMercado(mercado);
        return new ResponseEntity<Mercado>(newMercado, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Mercado> atualizarMercado(@PathVariable Long id, @RequestBody Mercado mercado){
        Mercado newMercado = mercadoService.updateMercado(id, mercado);
        return new ResponseEntity<Mercado>(newMercado, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mercado> deleteMercado(@PathVariable Long id){
        Mercado mercado = mercadoService.getMercadoByID(id);
        mercadoService.deleteMercado(mercado);
        return new ResponseEntity<Mercado>(mercado, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Mercado>> listarMercados(){
        Stream<Mercado> mercados = mercadoService.findAll();
        return new ResponseEntity<>(mercados, HttpStatus.OK);
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<Mercado> consultarMercado(@PathVariable Long id){
        Mercado mercado = mercadoService.getMercadoByID(id);
        return new ResponseEntity<Mercado>(mercado, HttpStatus.OK);
    }

    @GetMapping("/nick/{nick}")
    public ResponseEntity<Mercado> consultarMercado(@PathVariable String nick){
        Mercado mercado = mercadoService.getMercadoByNick(nick);
        return new ResponseEntity<Mercado>(mercado, HttpStatus.OK);
    }
}
