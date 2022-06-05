package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

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

import com.OPT.OPEasy.DTO.TransporteDTO;
import com.OPT.OPEasy.Service.TransporteService;
import com.OPT.OPEasy.model.Transporte;

@Controller
@CrossOrigin
@RequestMapping("/transporte")
public class TransporteController {
    
    @Autowired
    TransporteService transporteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Transporte> cadastrarTransporte(@RequestBody TransporteDTO transporte) throws Exception{
        Transporte newTransporte = transporteService.createTransporte(transporte);
        return new ResponseEntity<>(newTransporte,HttpStatus.ACCEPTED);
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Transporte> atualizarTransporte(@PathVariable Long id, @RequestBody TransporteDTO transporte) throws Exception{
        Transporte newTransporte = transporteService.atualizarTransporte(transporte, id);
        return new ResponseEntity<>(newTransporte,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Transporte> deletarTransporte(@PathVariable Long id) throws Exception{
        Transporte newTransporte = transporteService.deletarTransporte(id);
        return new ResponseEntity<>(newTransporte,HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Transporte>> listTransporte(){
        Stream<Transporte> list = transporteService.findAll();
        return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Transporte> consultarTransporte(@PathVariable Long id) throws Exception{
        Transporte transporte = transporteService.findById(id);
        return new ResponseEntity<>(transporte, HttpStatus.ACCEPTED);
    }

    @GetMapping("/mercado/{mercadoID}")
    public ResponseEntity<Stream<Transporte>> consultarTransportePorMercado(@PathVariable Long mercadoID) throws Exception{
        Stream<Transporte> transporte = transporteService.findByMercado(mercadoID);
        return new ResponseEntity<>(transporte, HttpStatus.OK);
    }
}
