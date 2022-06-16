package com.OPT.OPEasy.controller;

import java.util.stream.Stream;

import com.OPT.OPEasy.Service.EmpresaService;
import com.OPT.OPEasy.model.Empresa;

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
@RequestMapping("/empresa")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Empresa> cadastrarEmpresa(@RequestBody Empresa empresa){
        Empresa newEmpresa = empresaService.cadastrarEmpresa(empresa);
        return new ResponseEntity<Empresa>(newEmpresa, HttpStatus.ACCEPTED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) throws Exception{
        Empresa newEmpresa = empresaService.updateEmpresa(id, empresa);
        return new ResponseEntity<Empresa>(newEmpresa, HttpStatus.ACCEPTED);
    }

    @GetMapping("/list")
    public ResponseEntity<Stream<Empresa>> listarEmpresa(){
        Stream<Empresa> empresas = empresaService.findAll();
        return new ResponseEntity<Stream<Empresa>>(empresas, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Empresa> consultarEmpresa(@PathVariable Long id){
        Empresa empresa = empresaService.getEmpresaByID(id);
        return new ResponseEntity<Empresa>(empresa, HttpStatus.FOUND);
    }

    @GetMapping("/nick/{nick}")
    public ResponseEntity<Empresa> consultarEmpresa(@PathVariable String nick){
        Empresa empresa = empresaService.getEmpresaByNick(nick);
        return new ResponseEntity<Empresa>(empresa, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Empresa> deletarmpresa(@PathVariable Long id){
        Empresa empresa = empresaService.deleteEmpresa(id);
        return new ResponseEntity<Empresa>(empresa, HttpStatus.FOUND);
    }
}
